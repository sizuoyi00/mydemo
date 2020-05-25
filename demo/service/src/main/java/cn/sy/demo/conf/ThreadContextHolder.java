package cn.sy.demo.conf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ThreadContextHolder {

    private static ThreadLocal<HttpServletRequest> requestThreadLocalHolder = new ThreadLocal<>();
    private static ThreadLocal<HttpServletResponse> responseThreadLocalHolder = new ThreadLocal<>();
    private static ThreadLocal<HttpSession> sessionThreadLocal = new ThreadLocal<>();


    public static void setSession(HttpSession session) {
        sessionThreadLocal.set(session);
    }

    public static void getSession() {
        sessionThreadLocal.get();
    }

    public static void setHttpRequest(HttpServletRequest request) {

        requestThreadLocalHolder.set(request);
    }

    public static void setHttpResponse(HttpServletResponse response) {
        responseThreadLocalHolder.set(response);
    }


    public static void remove() {
        requestThreadLocalHolder.remove();
        responseThreadLocalHolder.remove();
        sessionThreadLocal.remove();
    }


    public static HttpServletResponse getHttpResponse() {

        return responseThreadLocalHolder.get();
    }

    public static HttpServletRequest getHttpRequest() {
        return requestThreadLocalHolder.get();
    }


}
