package cn.sy.demo.manager;

import cn.sy.demo.constant.SysConstans;
import cn.sy.demo.constant.enums.CacheConstsEnum;
import cn.sy.demo.constant.exception.BusinessException;
import cn.sy.demo.constant.role.JwtConstant;
import cn.sy.demo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;

@Component
@Slf4j
public class JwtAuthenticationManager {

    @Value("${spring.cloud.config.profile:dev}")
    private String profile;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name = "jwtUserService")
    private UserDetailsService jwtUserService;

    public void auth(HttpServletRequest req, HttpServletResponse resp) {

        //获取前端token
        String webToken = getToken(req);
        log.debug(" get webToken is :[{}]", webToken);

        String uuid = req.getHeader(JwtConstant.uuid);
        log.debug(" get uuid is :[{}]", uuid);

        if (Strings.isNotBlank(webToken)) {

            //获取授权
            UsernamePasswordAuthenticationToken authentication = getAuthentication(webToken, uuid, resp);

            if (!Objects.isNull(null)) {

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }
    }

    /**
     * 根据一个 webToken 生成授权
     *
     * @param webToken
     * @param resp
     * @return 授权
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String webToken, String uuid, HttpServletResponse resp) {

        if (Strings.isNotBlank(webToken)) {

            Claims claims = Jwts.parser()
                    .setSigningKey(JwtConstant.SECRET)
                    .parseClaimsJws(webToken).getBody();

            String jwtUserName = claims.getSubject();

            //获取前端传的token，通过该token拿到用户信息
            if (Strings.isNotBlank(jwtUserName) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {

                //通过token中用户信息，验证数据库用户信息
                final UserDetails userDetails = this.jwtUserService.loadUserByUsername(jwtUserName);

                //通过用户信息获取redis存储最长登录时间，如果登录时间超出，需要重新登录
                final String cacheToken = stringRedisTemplate.opsForValue().get(CacheConstsEnum.ACCESS_TOKEN.appendKeyByUnderline(uuid));
                if (Strings.isBlank(cacheToken)) {
                    throw new BusinessException("登录信息失效，请重新登录");
                }

                //用户信息无误，判断token是否需要reflush处理
                if (claims.getExpiration().before(new Date())) {
                    //失效时间早于当前时间，则说明token已经过期，需要说更新token失效时间
                    //后端直接更新token就好，token更新，原redis中token也需要删除
                    final String token = JwtUtils.generateToken(userDetails);
                    resp.setHeader(JwtConstant.HEADER_STRING, token);
                    stringRedisTemplate.delete(CacheConstsEnum.ACCESS_TOKEN.appendKeyByUnderline(uuid));
                    stringRedisTemplate.opsForValue().set(CacheConstsEnum.ACCESS_TOKEN.appendKeyByUnderline(uuid), token);
                }

                //用户信息无误，且token未失效
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                return authentication;

            }

        }

        return null;
    }

    /**
     * 获取前端header中的token
     *
     * @param req
     * @return
     */
    private String getToken(HttpServletRequest req) {

        String token = null;
        if (SysConstans.DEV.equals(profile) || SysConstans.TEST.equals(profile)) {

            token = req.getHeader(JwtConstant.HEADER_STRING);
            if (Strings.isNotBlank(token)) {

                token = token.replaceAll(JwtConstant.TOKEN_PREFIX, Strings.EMPTY).trim();
            }

            //生产环境token由redis中获取
        } else if (SysConstans.PROD.equals(profile)) {

            String uuid = req.getHeader(JwtConstant.uuid);
            if (uuid == null) {
                //TODO 设计前端永远生成uuid
            }

            token = stringRedisTemplate.opsForValue().get(CacheConstsEnum.ACCESS_TOKEN.appendKeyByUnderline(uuid));

        }

        return token;

    }

}
