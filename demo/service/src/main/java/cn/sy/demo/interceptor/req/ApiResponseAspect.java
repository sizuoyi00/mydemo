package cn.sy.demo.interceptor.req;

import cn.sy.demo.interceptor.res.ApiResponse;
import cn.sy.demo.interceptor.res.SkipResponseWrap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j
@Order(100)
public class ApiResponseAspect {

	private Gson gson = new GsonBuilder().serializeNulls().create();

	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void requestMapping() {
	}

	@Pointcut("within(cn.sy..*))")
	public void jf180() {
	}

	@Pointcut("requestMapping() && jf180()")
	public void apiResponse() {
	}

	@Around(value = "apiResponse()")
	public Object wrapResponseData(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = joinPoint.proceed();
		if (isWrapResponse(joinPoint)) {
			result = ApiResponse.from(result);
		}
		log.info("API response is {}", gson.toJson(result));
		return result;
	}

	/**
     * 不包装返回值的场景有两种
     * 1 历史遗留问题，controller自己返回的数据结构是ApiResponse
     * 2 方法上加了SkipResponseWrap注解的
     */
    private boolean isWrapResponse(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return method.getAnnotation(SkipResponseWrap.class) == null && method.getReturnType() != ApiResponse.class;
    }
}
