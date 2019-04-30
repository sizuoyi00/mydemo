package cn.sy.demo.aspect;


import cn.sy.demo.constant.exception.BusinessException;
import lombok.Data;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Iterator;
import java.util.Set;

@Data
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

    public static ApiResponse from(int code, String message) {
        ApiResponse response = new ApiResponse();
        response.code = code;
        response.message = message;
        return response;
    }
    
    public static ApiResponse from(Object data) {
        if(data instanceof ApiResponseCode) {
            return from((ApiResponseCode)data);
        }
        if(data instanceof BusinessException) {
            return from((BusinessException)data);
        }


        if(data instanceof BindException) {
            return from((BindException)data);
        }
        if(data instanceof ConstraintViolationException){
            return from((ConstraintViolationException)data);
        }
        if(data instanceof ValidationException){
            return from((ValidationException)data);
        }
        if(data instanceof MethodArgumentNotValidException){
            return from((MethodArgumentNotValidException)data);
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

    public static ApiResponse from(ConstraintViolationException ex) {
        ApiResponse response = new ApiResponse();
        response.code = ApiResponseCode.API_PARAMS_INVALID.getCode();
        final Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        final Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        if(iterator.hasNext()){
            response.message = iterator.next().getMessage();
        }else{
            response.message = ApiResponseCode.API_PARAMS_INVALID.getMessage();
        }
        return response;
    }

    public static ApiResponse from(ValidationException ex) {
        ApiResponse response = new ApiResponse();
        response.code = ApiResponseCode.API_PARAMS_INVALID.getCode();
        response.message = ex.getMessage();
        return response;
    }
    public static ApiResponse from(BindException ex) {
        ApiResponse response = new ApiResponse();
        BindingResult result = ex.getBindingResult();
        return bindResultRes(response, result);
    }

    public static ApiResponse from(MethodArgumentNotValidException ex) {
        ApiResponse response = new ApiResponse();
        BindingResult result = ex.getBindingResult();
        return bindResultRes(response, result);
    }

    private static ApiResponse bindResultRes(ApiResponse response, BindingResult result) {
        FieldError error = result.getFieldError();
        response.code = ApiResponseCode.API_PARAMS_INVALID.getCode();
        response.message = error.getDefaultMessage();
        return response;
    }

}
