package cn.sy.demo.service;

import cn.sy.demo.constant.role.JwtUser;

public interface AuthService {

    String login(String username, String password, String uuid);

    JwtUser register(JwtUser jwtUserToAdd);

}