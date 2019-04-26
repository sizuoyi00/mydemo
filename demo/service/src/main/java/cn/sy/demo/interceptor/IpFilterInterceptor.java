package cn.sy.demo.interceptor;

import cn.sy.demo.constant.exception.BusinessErrorCode;
import cn.sy.demo.constant.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author LeoYu
 * @date 2016年11月16日 下午5:46:26
 * 
 */
@Slf4j
@Component
public class IpFilterInterceptor extends HandlerInterceptorAdapter {

	@Value("#{'${demo.allow.ip.list}'.split(',')}")
	private List<String> ipList;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String ip = request.getHeader("X-Real-IP");
		log.debug(" ip is :" + ip);
		
		if (!ipList.contains(ip) && !ip.startsWith("10.")) {
			throw new BusinessException(BusinessErrorCode.IP_INVALID);
		}

		return super.preHandle(request, response, handler);
	}
}
