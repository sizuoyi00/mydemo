package cn.sy.demo.service.impl;

import cn.sy.demo.constant.enums.CacheConstsEnum;
import cn.sy.demo.constant.role.JwtConstant;
import cn.sy.demo.constant.role.JwtUser;
import cn.sy.demo.mapper.JwtUserMapper;
import cn.sy.demo.mapper.UserMapper;
import cn.sy.demo.model.JwtUserDo;
import cn.sy.demo.service.AuthService;
import cn.sy.demo.utils.JwtUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @www.codesheep.cn 20190312
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource(name = "jwtUserService")
    private UserDetailsService jwtUserService;

    @Autowired
    private JwtUserMapper jwtUserMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 登录
     */
    @Override
    public String login(String username, String password, String uuid) {

        //这几个不知道是干嘛的
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = jwtUserService.loadUserByUsername(username);
        final String token = JwtUtils.generateToken(userDetails);
        stringRedisTemplate.opsForValue().set(CacheConstsEnum.ACCESS_TOKEN.appendKeyByUnderline(uuid),
                token,JwtConstant.ACCESSTOKEN_TIMEOUT, TimeUnit.SECONDS);

        return token;
    }

    /**
     * 注册
     * @param jwtUser
     * @return
     */
    @Override
    public JwtUser register(JwtUser jwtUser) {

        final String username = jwtUser.getUsername();
        final Integer count = jwtUserMapper.selectCount(Wrappers.<JwtUserDo>lambdaQuery().eq(JwtUserDo::getUsername, username));
        if(count != 0){
            return null;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String password = jwtUser.getPassword();
        jwtUser.setPassword(encoder.encode(password));

        JwtUserDo jwtUserDo = new JwtUserDo();
        BeanUtils.copyProperties(jwtUser,jwtUserDo);
        jwtUserMapper.insert(jwtUserDo);

        return jwtUser;
    }
}
