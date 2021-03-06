package cn.sy.demo.conf;

import cn.sy.demo.interceptor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebAppConfig implements WebMvcConfigurer {

	@Value("${spring.profiles.active}")
	private String profile;

	@Autowired
	private RequestLogInterceptor requestLogInterceptor;

	@Autowired
	private RequestContextInterceptor requestContextInterceptor;

	@Autowired
	private IpFilterInterceptor ipFilterInterceptor;

	@Autowired
	private ClientVerifySigInterceptor clientVerifySigInterceptor;


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestLogInterceptor).addPathPatterns("/**");
		registry.addInterceptor(requestContextInterceptor).addPathPatterns("/**");
		registry.addInterceptor(ipFilterInterceptor).addPathPatterns("/**")
				.excludePathPatterns("/callback/**").excludePathPatterns("/notify/wxpay/**");
		registry.addInterceptor(clientVerifySigInterceptor).addPathPatterns("/")
				.addPathPatterns("/unicompay").addPathPatterns("/hs");

	}
}
