package cn.sy.demo.aspect.hbbank;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
@Aspect
@Slf4j
public class ApiResponseSigAspect {

	@Resource
	private SignUtil signUtil;

	/**
	 * execution(* cn.sy.demo.service.UserService.*(..))
	 * *表示返回任意类型
	 * 包+类+方法  可用*代
	 * (..)任务参数
	 */

//	表示匹配所有方法
//  @Pointcut("execution(* *(..))")
//	表示匹配cn.sy.demo.service中所有的公有方法
//	@Pointcut("execution(* cn.sy.demo.service.UserService.*(..))")
//	表示匹配com.sy.demo包及其子包下的所有方法
//	@Pointcut("execution(* com.sy.demo..*.*(..))")
//	表示匹配cn.sy.demo.conf.aspect.hbbank.HbBankAddSig的注解
	@Pointcut("@annotation(cn.sy.demo.aspect.hbbank.HbBankAddSig)")
	public void apiRespSig() {
	}

	@Around(value = "apiRespSig()")
	public Object addRespSig(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String,String> apiRes = null;
		Object response=joinPoint.proceed();
		if(response instanceof String){
			log.debug("hbbank retail response add sign...");
			apiRes = (Map) JSON.parse((String)response);
			apiRes.put("Sign",signUtil.sign(apiRes));
		}
		return JSON.toJSONString(apiRes);
	}

}
