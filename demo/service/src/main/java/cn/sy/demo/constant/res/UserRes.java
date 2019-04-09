package cn.sy.demo.constant.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
/**
 * 以后书写规则，res使用jackson注解相关
 * 当某个类中属性配置了@JsonProperty
 * 则返回值以此为准，不以全局配置为准
 */
public class UserRes {

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("password")
    private String passWord;

    @JsonFormat(pattern="yyyy/MM/dd")
    private Date currDate;



}
