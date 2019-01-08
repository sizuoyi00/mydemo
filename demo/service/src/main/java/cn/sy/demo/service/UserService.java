package cn.sy.demo.service;

import cn.sy.demo.model.User;

public interface UserService {

    User save(User user);

    User get(long id);

    void del(long id);

    User modify(User user);

}
