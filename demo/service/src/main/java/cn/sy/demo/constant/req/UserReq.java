package cn.sy.demo.constant.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
/**
 * 以后书写规则，req使用valid+json相关判断
 * controller参数+@Valid，则这里判断有效
 */
public class UserReq {

//    记录下Integer使用规则
//    @NotNull(message="id不能为空")
//    private Integer id;

    @NotEmpty(message="用户名不能为空")
    @JsonProperty("user_name")
    private String userName;

    @NotEmpty(message="密码不能为空")
//    @Pattern(regexp = "123|456",message = "密码错误")
    private String passWord;

//    @NotEmpty(message = "年龄不能为空")
//    private String age;

    //使用表单提交这里会报错，所以以后尽量规范使用json提交
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date date;

}
