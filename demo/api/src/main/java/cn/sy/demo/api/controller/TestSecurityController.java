package cn.sy.demo.api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSecurityController {

    /**
     * 测试普通权限
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    @RequestMapping( value="/normal/test", method = RequestMethod.GET )
    public Object test1() {
        return "ROLE_NORMAL /normal/test接口调用成功！";
    }

    /**
     * 测试管理员权限
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping( value = "/admin/test", method = RequestMethod.GET )
    public Object test2() {
        return "ROLE_ADMIN /admin/test接口调用成功！";
    }
}