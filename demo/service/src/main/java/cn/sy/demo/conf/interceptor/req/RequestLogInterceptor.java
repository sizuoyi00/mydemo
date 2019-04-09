package cn.sy.demo.conf.interceptor.req;

import cn.sy.demo.conf.context.ThreadContextHolder;
import cn.sy.demo.utils.IpUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
public class RequestLogInterceptor extends HandlerInterceptorAdapter {

    private static final List<String> EXCLUDE_SIGN_PARAMETER_NAMES = Arrays.asList(new String[] { "faceIdCardImg", "backIdCardImg", "faceRecognitionImg" });

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        try {
            ThreadContextHolder.setHttpRequest(request);
            ThreadContextHolder.setHttpResponse(response);
            String reqId = UUID.randomUUID().toString().replaceAll("-", "");
            Date reqTime = new Date();
            request.setAttribute("reqId", reqId);
            request.setAttribute("reqTime", reqTime);
            String ip = IpUtils.getIp(request);
            if (!StringUtils.isEmpty(ip)) {
                request.setAttribute("ip", ip);
            }
            logReqParams(reqId, ip, request, response);
        } catch (Exception e) {
        }
        return true;
    }

    private void logReqParams(String reqId, String ip, HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        JSONObject headerJson = new JSONObject();
        try {
            Enumeration<String> e = request.getHeaderNames();
            while (e.hasMoreElements()) {
                String param = e.nextElement();
                String val = request.getHeader(param);
                headerJson.put(param, val);
            }
        } catch (Exception e) {
        }

        JSONObject paramsJson = new JSONObject();
        try {
            Enumeration<String> e = request.getParameterNames();
            while (e.hasMoreElements()) {
                String param = e.nextElement();
                if (EXCLUDE_SIGN_PARAMETER_NAMES.contains(param)) {
                    continue;
                }
                String val = request.getParameter(param);
                paramsJson.put(param, val);
            }
        } catch (Exception e) {
        }
        Cookie[] cookies = request.getCookies();
        String cookieStr = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieString = cookie.getName() + "=" + cookie.getValue() + "," + cookie.getDomain() + "," + cookie.getPath() + ";";
                cookieStr = cookieStr + cookieString;
            }
        } else {
            cookieStr = "none";
        }
        log.info("Http请求[" + reqId + "]----->ip:" + ip + "," + uri + ",===header:" + headerJson.toJSONString() + "===params:" + paramsJson.toJSONString() + ",===cookies:" + cookieStr);
    }
}
