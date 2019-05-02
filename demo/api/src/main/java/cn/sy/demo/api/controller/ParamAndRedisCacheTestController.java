package cn.sy.demo.api.controller;

import cn.sy.demo.constant.UserReqGroup;
import cn.sy.demo.constant.validation.ForQuery;
import cn.sy.demo.model.User;
import cn.sy.demo.constant.UserReq;
import cn.sy.demo.constant.UserRes;
import cn.sy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;

@Validated
@RestController
@RequestMapping(value = "user")
public class ParamAndRedisCacheTestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "jackson/req",method = RequestMethod.GET)
    public Object testJacksonReq(){
        final User user = new User();
        user.setName("hehe");
        user.setCreateTime(LocalDateTime.now());
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

    @RequestMapping(value = "jackson/res2",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object testJacksonRes2(@Valid @RequestBody UserReq req){
//        UserRes res = new UserRes();
//        res.setPassWord(req.getPassWord());
//        res.setUserName(req.getUserName());
//        res.setCurrDate(new Date());
//        return res;
        return req;
    }

    @RequestMapping(value = "jackson/res3/{id}",method = RequestMethod.GET)
    public Object testJacksonRes2(@PathVariable("id")@Min(5)Integer id, @NotEmpty(message = "姓名不能为空") String name){
        UserRes res = new UserRes();
        res.setPassWord(id+"");
        res.setUserName(name);
        res.setCurrDate(new Date());
        return res;
    }

    /**
     * @Validated(ForQuery.class)
     * 通过@Validate注解标识要验证的分组
     * @param req
     * @return
     */
    @RequestMapping(value = "jackson/resgroup",method = RequestMethod.GET)
    public Object testRes(@Validated(ForQuery.class) UserReqGroup req){
        return req;
    }

    /**
     * 如果这里不指定校验组，则会调用没有定义组的校验
     * @param req
     * @return
     */
    @RequestMapping(value = "jackson/resgroup2",method = RequestMethod.GET)
    public Object testRes2(@Valid UserReqGroup req){
        return req;
    }

    @RequestMapping(value = "get/{id}",method = RequestMethod.GET)
    public Object getUser(@PathVariable Integer id){
        return userService.get(id);
    }

}
