package cn.sy.demo.api.controller;

import cn.sy.demo.constant.UserInfoVO;
import cn.sy.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "user-info")
@Validated
public class UserInfoController {

    @Autowired
    public UserInfoService userInfoService;

    /**
     * 用户注册
     * 流程：邀请码验证->手机号->手机验证码->生日->用户名loginId->密码
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "registered",method = RequestMethod.POST)
    public Object registered(@Valid @RequestBody UserInfoVO userInfo){

        userInfoService.registered(userInfo);

        return null;
    }

    /**
     * 邀请码信息验证
     * @param invitationCode
     * @return
     */
    @RequestMapping(value = "judge/invitation_code",method = RequestMethod.GET)
    public Object judgeInvitationCode(@NotNull(message = "邀请码不能为空") String invitationCode){

        return userInfoService.judgeInvitationCode(invitationCode);

    }

    /**
     * 发送短信验证码
     * @param mobile
     * @return
     */
    @RequestMapping(value = "registered/send",method = RequestMethod.GET)
    @ResponseBody
    public Object registerCodeSend(@NotEmpty(message = "手机号不能为空")String mobile){

        return userInfoService.registeredSms(mobile);
    }

    /**
     * 短信码验证
     * @param mobile
     * @param code
     * @return
     */
    @RequestMapping(value = "registered/valid",method = RequestMethod.POST)
    public Object valSmsCode(@NotEmpty(message = "手机号不能为空")String mobile, @NotEmpty(message = "手机验证码不能为空")String code){
        return userInfoService.valSmsCode(mobile,code);

    }

}
