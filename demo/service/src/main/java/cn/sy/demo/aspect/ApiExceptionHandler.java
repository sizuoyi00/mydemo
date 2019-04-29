package cn.sy.demo.aspect;

import cn.sy.demo.constant.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@ControllerAdvice
@Order
@Slf4j
public class ApiExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public Object handleNoHandlerFoundException(Throwable e) {
		log.error("API Url invalid", e);
		return ApiResponse.from(ApiResponseCode.API_NOT_FOUND);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public Object handleRequestMethodNotSupportedException(Throwable e) {
		log.warn("API request method not supported", e);
		return ApiResponse.from(ApiResponseCode.API_REQUEST_METHOD_NOT_SUPPORTED);
	}

	@ExceptionHandler({MissingServletRequestParameterException.class, HttpMessageNotReadableException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Object handleParameterValidateException(Throwable e) {
		log.warn("parameter validate failed", e);
		return ApiResponse.from(ApiResponseCode.API_PARAMS_INVALID);
	}

	/**
	 * 通过公共校验模板返回给前端的异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class, MethodArgumentNotValidException.class})
	@ResponseBody
	public Object handleParameterToViewException(Throwable e) {
		log.warn("parameter validate failed", e);
		return ApiResponse.from(e);
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public Object handleException(BusinessException e) {
		log.info("business exception {}", e.toString());
		return ApiResponse.from(e);
	}

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public Object handleException(Throwable e) {
		log.error("API request exception", e);
		return ApiResponse.from(ApiResponseCode.SYS_ERROR);
	}
}