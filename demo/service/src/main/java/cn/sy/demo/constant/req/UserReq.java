package cn.sy.demo.constant.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
/**
 * 以后书写规则，req使用valid相关判断
 * controller参数+@Valid，则这里判断有效
 */
public class UserReq {

//    记录下Integer使用规则
//    @NotNull(message="id不能为空")
//    private Integer id;

    @NotEmpty(message="用户名不能为空")
    private String userName;

    @NotEmpty(message="密码不能为空")
//    @Pattern(regexp = "123|456",message = "密码错误")
    private String passWord;


}
