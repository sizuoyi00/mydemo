package cn.sy.demo.service.impl;

import cn.sy.demo.model.JwtUser;
import cn.sy.demo.mapper.JwtUserMapper;
import cn.sy.demo.service.JwtUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guests
 * @since 2019-05-16
 */
@Service
public class JwtUserServiceImpl extends ServiceImpl<JwtUserMapper, JwtUser> implements JwtUserService {

}
