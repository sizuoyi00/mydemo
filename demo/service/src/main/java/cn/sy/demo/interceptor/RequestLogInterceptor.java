package cn.sy.demo.interceptor;

import cn.sy.demo.utils.IpUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
@Component
public class RequestLogInterceptor extends HandlerInterceptorAdapter {

    private static final List<String> EXCLUDE_SIGN_PARAMETER_NAMES = Arrays.asList(new String[] { "faceIdCardImg", "backIdCardImg", "faceRecognitionImg" });

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        try {
            String reqId = UUID.randomUUID().toString().replaceAll("-", "");
            String ip = IpUtils.getIp(request);
            if (!StringUtils.isEmpty(ip)) {
                request.setAttribute("ip", ip);
            }
            logReqParams(reqId, ip, request);
        } catch (Exception e) {
            log.debug("request log error",e);
        }
        return true;
    }

    private void logReqParams(String reqId, String ip, HttpServletRequest request) {
        String uri = request.getRequestURI();

        //header信息
        JSONObject headerJson = new JSONObject();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String param = headerNames.nextElement();
            String val = request.getHeader(param);
            headerJson.put(param, val);
        }

        //cookie信息
        Cookie[] cookies = request.getCookies();
        StringBuffer cookieStr = new StringBuffer();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieStr.append(cookie.getName()).append("=").append(cookie.getValue()).append(",")
                        .append(cookie.getDomain()).append(",").append(cookie.getPath()).append(";");
            }
        } else {
            cookieStr.append("none");
        }

        //参数信息
        JSONObject paramsJson = new JSONObject();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String param = parameterNames.nextElement();
            if (EXCLUDE_SIGN_PARAMETER_NAMES.contains(param)) {
                continue;
            }
            String val = request.getParameter(param);
            paramsJson.put(param, val);
        }

        log.info("Http请求[{}]----->ip:[{}],===url:[{}],===header:[{}],===cookies:[{}],===params:[{}]", reqId, ip, uri, headerJson, cookieStr, paramsJson);
    }
}
