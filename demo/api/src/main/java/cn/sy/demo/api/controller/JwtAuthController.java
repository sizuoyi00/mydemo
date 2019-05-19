package cn.sy.demo.api.controller;

import cn.sy.demo.constant.role.JwtConstant;
import cn.sy.demo.constant.role.JwtUser;
import cn.sy.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class JwtAuthController {

    @Autowired
    private AuthService authService;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public String createToken( String username,String password, HttpServletRequest request){
        // 登录成功会返回JWT Token给用户
        return authService.login( username, password, request.getHeader(JwtConstant.uuid));
    }

    /**
     * 注册
     * @param addedJwtUser
     * @return
     */
    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    public JwtUser register(@RequestBody JwtUser addedJwtUser) {
        return authService.register(addedJwtUser);
    }
}