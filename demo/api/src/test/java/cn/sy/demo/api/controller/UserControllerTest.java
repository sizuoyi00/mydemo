package cn.sy.demo.api.controller;

import cn.sy.demo.model.User;
import cn.sy.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;

@Slf4j
public class UserControllerTest extends BaseControllerTest {

    @Resource
    private UserService userService;

    @Test
    public void save(){
        User user = new User();
        user.setName("hhehehehehhehe");
        user.setId(7089800L);
        User u= userService.save(user);
        log.info("11111,{}", u.getName());
    }

    @Test
    public void motify(){
        User user = new User();
        user.setName("222222222222222");
        user.setId(7089800L);
        User u= userService.modify(user);
        log.info("11111,{}", u.getName());
    }

    @Test
    public void get(){
        User u= userService.get(213L);
    }

    @Test
    public void del(){
         userService.del(213L);
    }

}
