package cn.sy.demo.manager;

import cn.sy.demo.mapper.UserMapper;
import cn.sy.demo.model.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserManager {

    @Resource
    private UserMapper userMapper;

    
    /**
     * 只有该方法成功返回，才会加缓存
     * 该注解的value匹配到cacheconfig中map的key时，则使用对应的配置
     * return 一定要返回要想要缓存的对象
     * @param user
     * @return
     */
//    @CachePut(value = {"user"},key ="\"user_\" + #user.id")
//    @CachePut(value = "user", key ="#root.methodName+'_'+#user.id")
    @CachePut(value = "DEMO_USER", key ="#root.methodName+'_'+#user.id")
    public User saveUser(User user) {
        userMapper.insert(user);
        return user;
    }

    /**
     * 重新调用后 修改后失效时间
     * @param user
     * @return
     */
    @CachePut(value = {"user"},key ="\"user_\" + #user.id")
    public User modify(User user) {
        userMapper.updateById(user);
        return user;
    }

    /**
     * return 一定要返回要想要缓存的对象
     * 只有不为空才会加到缓存中
     * 缓存中有值不会刷新缓存时间
     * @param id
     * @return
     */
    @Cacheable(value = {"user"},key ="\"user_\" + #id")
    public User get(long id) {
        final User user = userMapper.selectById(id);
        return user;
    }

    /**
     * condition 与 unless区别
     * unless只能阻止将对象放进缓存，方法调用时，仍会去缓存查找，找到则返回。
     * condition则是将缓存禁用，不会去缓存查找，也不会将返回值放到缓存
     * @param id
     */
    @CacheEvict(value = "user",key = "\"user_\" + #id",condition = "#id != -1")
    public void del(long id) {
        userMapper.deleteById(id);
    }

}
