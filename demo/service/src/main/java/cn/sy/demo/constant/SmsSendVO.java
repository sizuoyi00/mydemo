package cn.sy.demo.constant;

import lombok.Data;

@Data
public class SmsSendVO {

    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 手机短信内容
     */
    private String content;
    /**
     * 验证码场景
     */
    private String sceneType;

}
