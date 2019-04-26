package cn.sy.demo.interceptor;

import cn.sy.demo.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * ip访问次数限制，防止攻击
 */
@Slf4j
@Component
public class RateLimitInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final int LIMIT_TIME = 30;// 每30秒允许访问
    private final int LIMIT_NUMBER = 3; //同一ip限制访问次数

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String ip = IpUtils.getIp(request);

        String key = "reserveCouponLimit_" + ip;
        long currentNumber = stringRedisTemplate.opsForValue().increment(key, 1);
        log.debug("rate limit key is {}, current number is {}", key, currentNumber);

        if (currentNumber == 1) {//从第一次访问设置过期时间
            stringRedisTemplate.expire(key, LIMIT_TIME, TimeUnit.SECONDS);
        }

        if (currentNumber > LIMIT_NUMBER) {
            log.info("{} request reserve coupon times is {} beyond to LIMIT_NUMBER {}", key, currentNumber, LIMIT_NUMBER);
//            response.getWriter().println(JSONObject.fromObject(JsonResultUtil.getErrorJson("对不起，您的访问过于频繁，请稍后再试！")));
            return false;
        }

        return super.preHandle(request, response, handler);
    }

}
