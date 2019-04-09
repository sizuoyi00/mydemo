package cn.sy.demo.interceptor.res;


import cn.sy.demo.constant.exception.BusinessException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiResponse {

    private int code = 0;

    private String message="";

    private Object data;
    
    private String sign;
    
    public static ApiResponse from(ApiResponseCode code) {
        ApiResponse response = new ApiResponse();
        response.code = code.getCode();
        response.message = code.getMessage();
        return response;
    }
    
    public static ApiResponse from(Object data) {
        if(data instanceof ApiResponseCode) {
            return from((ApiResponseCode)data);
        }
        if(data instanceof BusinessException) {
            return from((BusinessException)data);
        }
        
        ApiResponse response = new ApiResponse();
        response.data = data;
        return response;
    }
    
    public static ApiResponse from(BusinessException ex) {
        ApiResponse response = new ApiResponse();
        response.code = ex.getCode();
        response.message = ex.getMessage();
        return response;
    }
    
}
