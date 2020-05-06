package cn.sy.demo.web;

import cn.sy.demo.constant.role.JwtConstant;
import cn.sy.demo.constant.role.JwtUser;
import cn.sy.demo.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@Api(value = "jwtauth")
public class JwtAuthController {

    @Autowired
    private AuthService authService;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @ApiOperation("login")
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public Object createToken(String username, String password, HttpServletRequest request) {
        // 登录成功会返回JWT Token给用户
        return authService.login(username, password, request.getHeader(JwtConstant.uuid));
    }

    /**
     * 注册
     *
     * @param addedJwtUser
     * @return
     */
    @ApiOperation("register")
    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    public Object register(@RequestBody JwtUser addedJwtUser) {
        val jwtUser = authService.register(addedJwtUser);
        return !Objects.isNull(jwtUser);
    }
}