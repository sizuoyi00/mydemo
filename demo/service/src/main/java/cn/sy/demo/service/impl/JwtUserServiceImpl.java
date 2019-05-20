package cn.sy.demo.service.impl;

import cn.sy.demo.constant.role.JwtUser;
import cn.sy.demo.mapper.JwtUserMapper;
import cn.sy.demo.model.JwtUserDo;
import cn.sy.demo.service.JwtUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guests
 * @since 2019-05-16
 */
@Service("jwtUserService")
public class JwtUserServiceImpl extends ServiceImpl<JwtUserMapper, JwtUserDo> implements JwtUserService,UserDetailsService {

    @Autowired
    private JwtUserMapper jwtUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JwtUserDo jwtUserDo = jwtUserMapper.selectUserWithRoleByUserName(username);
        if (jwtUserDo == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        JwtUser jwtUser = new JwtUser();
        BeanUtils.copyProperties(jwtUserDo,jwtUser);
        return jwtUser;
    }

}
