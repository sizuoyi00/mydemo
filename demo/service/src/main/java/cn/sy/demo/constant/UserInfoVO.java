package cn.sy.demo.constant;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserInfoVO {

    @NotEmpty(message = "邀请码不能为空")
    private String invitationCode;

    @NotEmpty(message = "手机号不能为空")
    private String mobile;

    @NotEmpty(message = "生日信息不能为空")
    private String birthday;

    @NotEmpty(message = "用户名不能为空")
    private String loginId;

    @NotEmpty(message = "账户密码不能为空")
    private String pwd;

}
