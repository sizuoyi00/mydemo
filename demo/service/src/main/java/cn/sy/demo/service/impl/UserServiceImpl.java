package cn.sy.demo.service.impl;

import cn.sy.demo.manager.UserManager;
import cn.sy.demo.mapper.UserMapper;
import cn.sy.demo.model.User;
import cn.sy.demo.service.UserService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户基础信息 服务实现类
 * 当我们选择使用rediscache时，要注意rediscache的方法返回值要返回该对象才可以，
 * 所以我们抽出一层manager,做缓存处理，当我们不需要rediscache时，自己判断调用manager
 * </p>
 *
 * @author guests
 * @since 2019-05-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserManager userManager;

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

    @Override
    public void saveUser(User user) {
        userManager.saveUser(user);
    }

    /**
     * 重新调用后 修改后失效时间
     * @param user
     * @return
     */
    @Override
    public void modify(User user) {
        userManager.modify(user);
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
        final User user = userMapper.selectById(id);
        return user;
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
        userMapper.deleteById(id);
    }

}
