package cn.sy.demo.api.controller;

import cn.sy.demo.mapper.JwtUserMapper;
import cn.sy.demo.model.JwtUser;
import cn.sy.demo.model.User;
import cn.sy.demo.model.UserInfo;
import cn.sy.demo.service.UserInfoService;
import cn.sy.demo.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class MybatisPlusTest extends BaseControllerTest{

    @Resource
    private UserService userService;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private JwtUserMapper jwtUserMapper;

    /**
     * 表的联级查询
     */
    @Test
    public void testCascade(){
        final JwtUser user = jwtUserMapper.selectUserWithRoleByUserName("sss");
        System.out.println(user);
    }

    /**
     * 表自动联级查询
     */
    @Test
    public void testAutoCascade(){
        final JwtUser user = jwtUserMapper.selectUserAutoWithRoleByUserName("sss");
        System.out.println(user);
    }

    @Test
    public void getUserInfo(){
        final UserInfo user = userInfoService.getUser(1);
        System.out.println(user);
    }

    @Test
    public void updateUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginId("1");
        final int i = userInfoService.updateUserInfo(userInfo);
        System.out.println(i);
    }

    @Test
    public void getUser(){
        final User user = userService.getUser("1");
        System.out.println(user);
    }

    @Test
    public void getUserPlus(){
        final List<User> userPlus = userService.getUserPlus("1");
        userPlus.forEach(System.out::println);
    }

    @Test
    public void getUserPage(){
        final IPage<User> userIPage = userService.getUserPage(1, 5);
        System.out.println("总条数 ------> " + userIPage.getTotal());
        System.out.println("当前页数 ------> " + userIPage.getCurrent());
        System.out.println("当前每页显示数 ------> " + userIPage.getSize());
        userIPage.getRecords().forEach(System.out::println);
        System.out.println("----- baseMapper 自带分页 ------");
    }

}
