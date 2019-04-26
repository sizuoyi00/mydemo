package cn.sy.demo.constant.exception;

import lombok.Getter;

@Getter
public enum BusinessErrorCode {
    
    IP_INVALID (1002, "访问非法"),
    REQUEST_FREQUENTLY (1005, "请求过于频繁"),
    SIGN_INVALID(1004, "验签失败");

    private int code;

    private String message;

    BusinessErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
