package cn.sy.demo.constant;

import cn.sy.demo.constant.validation.ForCreate;
import cn.sy.demo.constant.validation.ForQuery;
import cn.sy.demo.constant.validation.ForUpdate;
import cn.sy.demo.manager.validation.IpAddress;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
/**
 * 校验参数组
 *
 * 经常会有两个相同的Service使用同一个领域对象。
 * 比如在实现CRUD操作时，创建操作和更新操作很可能使用用一个对象作为参数，
 * 但是在两种情况下可能会触发不同的验证：新增，修改
 *
 * @Validated(ForQuery.class)  controller指定要验证的分组
 * 不指定，默认校验没有分组的校验
 *
 */
public class UserReqGroup {


    @NotEmpty(message="id不能为空", groups = ForUpdate.class)
    private String id;

    @NotEmpty(message="用户名不能为空", groups = {ForUpdate.class, ForCreate.class, ForQuery.class})
    @JsonProperty("user_name")
    private String userName;

    @NotEmpty(message="密码不能为空", groups = {ForUpdate.class, ForCreate.class, ForQuery.class})
//    @Pattern(regexp = "123|456",message = "密码错误")
    private String passWord;

//    @NotEmpty(message = "年龄不能为空")
//    private String age;

    //使用表单提交这里会报错，所以以后尽量规范使用json提交
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date date;

    @NotNull(message = "ipAdress不能为空")
    @IpAddress(message = "ip不符合规范", groups = ForQuery.class)
    private String ipAdress;

}
