package cn.sy.demo.service.impl;

import cn.sy.demo.mapper.UserMapper;
import cn.sy.demo.model.User;
import cn.sy.demo.model.UserExample;
import cn.sy.demo.service.UserService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户基础信息 服务实现类
 * </p>
 *
 * @author guests
 * @since 2019-05-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUser(String idCard) {
        return userMapper.getUser(idCard);
    }

    @Override
    @DS("local")
    public List<User> getUserPlus(String idCard) {
        final List<User> users = userMapper.selectList(new QueryWrapper<User>().lambda().eq(User::getIdCard, idCard));
        return users;
    }

    @Override
    public IPage<User> getUserPage(int index, int size) {
        Page<User> page = new Page<>(index, size);
        IPage<User> userIPage = userMapper.selectPage(page, Wrappers.<User>lambdaQuery()
                .eq(User::getName, "hhehehehehhehe"));
        return userIPage;

    }

    /**
     * 只有该方法成功返回，才会加缓存
     * 该注解的value匹配到cacheconfig中map的key时，则使用对应的配置
     * return 一定要返回要想要缓存的对象
     * @param user
     * @return
     */
    @Override
//    @CachePut(value = {"user"},key ="\"user_\" + #user.id")
//    @CachePut(value = "user", key ="#root.methodName+'_'+#user.id")
    @CachePut(value = "DEMO_USER", key ="#root.methodName+'_'+#user.id")
    public User saveUser(User user) {
//        userMapper.insertSelective(user);
        return user;
    }

    /**
     * 重新调用后 修改后失效时间
     * @param user
     * @return
     */
    @Override
    @CachePut(value = {"user"},key ="\"user_\" + #user.id")
    public User modify(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(user.getId());
//        userMapper.updateByExampleSelective(user,example);
        return user;
    }

    /**
     * return 一定要返回要想要缓存的对象
     * 只有不为空才会加到缓存中
     * 缓存中有值不会刷新缓存时间
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = {"user"},key ="\"user_\" + #id")
    public User get(long id) {
//        final User user = userMapper.selectByPrimaryKey(id);
        return null;
    }

    /**
     * condition 与 unless区别
     * unless只能阻止将对象放进缓存，方法调用时，仍会去缓存查找，找到则返回。
     * condition则是将缓存禁用，不会去缓存查找，也不会将返回值放到缓存
     * @param id
     */
    @Override
    @CacheEvict(value = "user",key = "\"user_\" + #id",condition = "#id != -1")
    public void del(long id) {
//        userMapper.deleteByPrimaryKey(id);
    }

}
