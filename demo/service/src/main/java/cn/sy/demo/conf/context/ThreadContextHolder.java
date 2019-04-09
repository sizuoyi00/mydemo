package cn.sy.demo.conf.context;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class ThreadContextHolder {

	private static ThreadLocal<HttpSession> SessionThreadLocalHolder = new ThreadLocal<HttpSession>();
	private static ThreadLocal<Integer> sApiMemberId = new ThreadLocal<>();
	private static ThreadLocal<HttpServletRequest> HttpRequestThreadLocalHolder = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> HttpResponseThreadLocalHolder = new ThreadLocal<HttpServletResponse>();

	public static void setHttpRequest(HttpServletRequest request) {
		HttpRequestThreadLocalHolder.set(request);
	}

	public static HttpServletRequest getHttpRequest() {
		return HttpRequestThreadLocalHolder.get();
	}

	public static void remove() {
		SessionThreadLocalHolder.remove();
		HttpRequestThreadLocalHolder.remove();
		HttpResponseThreadLocalHolder.remove();
	}

	public static void setHttpResponse(HttpServletResponse response) {
		HttpResponseThreadLocalHolder.set(response);
	}

	public static HttpServletResponse getHttpResponse() {

		return HttpResponseThreadLocalHolder.get();
	}

	public static void setSession(HttpSession session) {
		SessionThreadLocalHolder.set(session);
	}

	public static HttpSession getSession() {
		HttpSession httpSession = SessionThreadLocalHolder.get();
		if (httpSession == null) {
			HttpServletRequest httpRequest = getHttpRequest();
			SessionThreadLocalHolder.set((httpRequest != null) ? httpRequest.getSession() : null);
		} else {
			 log.debug(" webSessionContext not null and return ...");
		}
		httpSession = SessionThreadLocalHolder.get();
		if (httpSession != null) {
			int maxInactiveInterval = httpSession.getMaxInactiveInterval();
			if (maxInactiveInterval < 129600) {
				httpSession.setMaxInactiveInterval(129600);
			}
		}
		return httpSession;
	}

	public static void setApiMemberId(Integer memberId) {

		sApiMemberId.set(memberId);
	}

	public static Integer getApiMemberId() {
		return sApiMemberId.get();
	}

}
