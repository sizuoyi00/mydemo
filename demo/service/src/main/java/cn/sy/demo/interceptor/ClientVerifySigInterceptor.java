package cn.sy.demo.interceptor;

import cn.sy.demo.constant.exception.BusinessErrorCode;
import cn.sy.demo.constant.exception.BusinessException;
import cn.sy.demo.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
@Component
public class ClientVerifySigInterceptor extends HandlerInterceptorAdapter {

	private static final List<String> EXCLUDE_SIGN_PARAMETER_NAMES = Arrays.asList(new String[] { "sig" });

	@Value("#{'${demo.verify.ip.list}'.split(',')}")
	private List<String> ipList;
	
	private Map<String,String> merchantSecretKeyMap;
	@Value("${demo.secret.key}")
	private String demoSecretKey;

	@PostConstruct
	public void init(){
		merchantSecretKeyMap=new HashMap<>();
		merchantSecretKeyMap.put("", demoSecretKey);

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("ClientVerifySigInterceptor...");

		String ip = request.getHeader("X-Real-IP");
		log.debug(" ip is :" + ip);
		if (ipList.contains(ip)) {
			log.debug("{} ip in verify ip list, not need verify sig", ip);
			return super.preHandle(request, response, handler);
		}

		String sig = request.getParameter("sig");
		if (StringUtils.isBlank(sig)) {
			throw new BusinessException(BusinessErrorCode.SIGN_INVALID);
		}

		StringBuilder sBuilder = new StringBuilder();

		List<String> paramNames = getSortedParameterNames(request);
		for (String name : paramNames) {
			sBuilder.append(name);
			sBuilder.append(request.getParameter(name));
		}

		String demo = request.getParameter("demo");
		String secretKey = merchantSecretKeyMap.get(demo);
		sBuilder.append(secretKey);

		String plainSign = sBuilder.toString();
		log.debug("plain sign is {}", plainSign);

		String expectedSign = MD5Utils.md5(plainSign);
		log.debug("expected sign is {}", expectedSign);

		if (!sig.equals(expectedSign)) {
			log.warn("param sig is {}", sig);
			throw new BusinessException(BusinessErrorCode.SIGN_INVALID);
		}

		return super.preHandle(request, response, handler);
	}

	private List<String> getSortedParameterNames(HttpServletRequest request) {
		List<String> keys = new ArrayList<String>();
		Enumeration<String> parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String name = parameterNames.nextElement();
			if (EXCLUDE_SIGN_PARAMETER_NAMES.contains(name)) {
				continue;
			}
			keys.add(name);
		}

		Collections.sort(keys);
		return keys;
	}
	
}
