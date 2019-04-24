package cn.sy.demo.aspect;

import lombok.Getter;

@Getter
public enum ApiResponseCode {

    SUCCESS (0, "SUCCESS"),
    BUSINESS_EXCEPTION(2000, ""),
    SYS_ERROR (9000, "系统异常，请联系客服人员"),
    API_NOT_FOUND(9001, "API不存在"),
    API_PARAMS_INVALID(9002, "API请求参数非法"),
    API_REQUEST_METHOD_NOT_SUPPORTED(9003, "API请求方法不支持");

    private int code;

    private String message;

    ApiResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
