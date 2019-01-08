package cn.sy.demo.filter;

import lombok.Setter;
import org.springframework.core.Ordered;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 编码过滤器
 */
@Setter
public class MutiCharacterEncodingFilter extends CharacterEncodingFilter implements Ordered {

    //最高优先级
    private int order = Integer.MIN_VALUE;

    private List<String> mutiUrls = new ArrayList();

    private String mutiCharset;

    public MutiCharacterEncodingFilter(String charset, String mutiCharset, List<String> mutiUrls, boolean forceRequest, boolean forceResponse) {
        super(charset, forceRequest, forceResponse);
        this.mutiUrls = mutiUrls;
        this.mutiCharset = mutiCharset;

    }

    public MutiCharacterEncodingFilter() {
        super();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        if (matchAny(mutiUrls, path)) {
            if (mutiCharset != null) {
                if (isForceRequestEncoding() || request.getCharacterEncoding() == null) {
                    request.setCharacterEncoding(mutiCharset);
                }
                if (isForceResponseEncoding()) {
                    response.setCharacterEncoding(mutiCharset);
                }
            }
            filterChain.doFilter(request, response);
            return;
        }

        super.doFilterInternal(request, response, filterChain);

    }


    @Override
    public int getOrder() {
        return order;
    }

    /**
     * 匹配url
     * @param mutiUrls
     * @param path
     * @return
     */
    public static boolean matchAny(List<String> mutiUrls, String path) {
        for (String mutiUrl : mutiUrls) {
            if (mutiUrl.equals(path)){
                return true;
            }
        }
        return false;
    }
}