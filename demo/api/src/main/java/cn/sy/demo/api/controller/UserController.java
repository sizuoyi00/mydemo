package cn.sy.demo.api.controller;

import cn.sy.demo.model.User;
import cn.sy.demo.constant.req.UserReq;
import cn.sy.demo.constant.res.UserRes;
import cn.sy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

@Validated
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "jackson/req",method = RequestMethod.GET)
    public Object testJacksonReq(){
        final User user = new User();
        user.setName("hehe");
        user.setFinName("hehehehe");
        user.setFinCreditTime(new Date());
        return user;
    }

    /**
     * http://localhost:8090/demo/user/jackson/res?user_name=123$pass_word=123
     * 结果userName passWord req = null
     * http://localhost:8090/demo/user/jackson/res?userName=123&passWord=123
     * 结果userName passWord req != null
     * @param userName
     * @param passWord
     * @return
     */
    @RequestMapping(value = "jackson/res",method = RequestMethod.GET)
    public Object testJacksonRes(@Valid UserReq req){
        UserRes res = new UserRes();
        res.setUserName(req.getUserName());
        res.setPassWord(req.getPassWord());
        res.setCurrDate(new Date());
        return res;
    }

    @RequestMapping(value = "get/{id}",method = RequestMethod.GET)
    public Object getUser(@PathVariable Integer id){
        return userService.get(id);
    }

}
