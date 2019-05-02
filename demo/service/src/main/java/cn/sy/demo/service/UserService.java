package cn.sy.demo.service;

import cn.sy.demo.model.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户基础信息 服务类
 * </p>
 *
 * @author guests
 * @since 2019-05-03
 */
public interface UserService extends IService<User> {

    User saveUser(User user);

    User get(long id);

    void del(long id);

    User modify(User user);

    User getUser(String idCard);

    List<User> getUserPlus(String idCard);

    IPage<User> getUserPage(int index, int size);

}
