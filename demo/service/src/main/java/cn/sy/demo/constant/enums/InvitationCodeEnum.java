package cn.sy.demo.constant.enums;

import lombok.Getter;

@Getter
public enum InvitationCodeEnum {
    INIT("INIT","初始化"),
    USED("USED","已使用");

    private String code;
    private String desc;

    InvitationCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
