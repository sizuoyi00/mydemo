package cn.sy.demo.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class IpUtils {

    public static String getIp(HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        try {
            String ip = request.getHeader("X-Forwarded-For");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("x-real-ip");
                }
                if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("Proxy-Client-IP");
                }
                if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("WL-Proxy-Client-IP");
                }
                if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_CLIENT_IP");
                }
                if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                }
                if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getRemoteAddr();
                }
            } else if (ip.length() > 15) {
                String[] ips = ip.split(",");
                for (int i = 0; i < ips.length; i++) {
                    String strIp = ips[i];
                    if (!"unknown".equalsIgnoreCase(strIp)) {
                        ip = strIp;
                        break;
                    }
                }
            }
            return ip;
        } catch (Exception e) {

        }
        return "";
    }

}
