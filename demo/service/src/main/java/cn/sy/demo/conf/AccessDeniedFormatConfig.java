package cn.sy.demo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 验权失败的异常格式定义
 *
 */

@Configuration
public class AccessDeniedFormatConfig {


    /**
     * 定义认证失败的格式
     *
     * @return
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.setStatus(401);
            httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
            httpServletResponse.getWriter().print("{\"code\":\"001\",\"message\":\"无权访问\"}");
            httpServletResponse.getWriter().flush();
        };
    }

    /**
     * 定义认证失败的格式
     *
     * @return
     */

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, httpServletResponse, authException) -> {
            httpServletResponse.setStatus(401);
            httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
            httpServletResponse.getWriter().print("{\"code\":\"001\",\"message\":\"您尚未登录或登录已超时\"}");
            httpServletResponse.getWriter().flush();
        };
    }

}
