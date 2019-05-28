package cn.sy.demo.interceptor;

import cn.sy.demo.aspect.RequestLimit;
import cn.sy.demo.constant.enums.CacheConstsEnum;
import cn.sy.demo.constant.exception.BusinessException;
import cn.sy.demo.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * 访问次数限制，防止攻击
 */
@Slf4j
@Component
public class RequestLimitInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * isAssignableFrom() 判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口
         * isAssignableFrom()方法是判断是否为某个类的父类
         * instanceof关键字是判断是否某个类的子类
         */
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            //HandlerMethod 封装方法定义相关的信息,如类,方法,参数等
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            // 获取方法中是否包含注解
            RequestLimit methodAnnotation = method.getAnnotation(RequestLimit.class);
            //获取 类中是否包含注解，也就是controller 是否有注解
            RequestLimit classAnnotation = method.getDeclaringClass().getAnnotation(RequestLimit.class);
            // 如果 方法上有注解就优先选择方法上的参数，否则类上的参数
            RequestLimit requestLimit = Objects.isNull(methodAnnotation) ? classAnnotation : methodAnnotation;
            if (!Objects.isNull(requestLimit)) {
                if (isLimit(request, requestLimit)) {
                    throw new BusinessException("请求过于频繁");
                }
            }
        }

        return super.preHandle(request, response, handler);
    }

    /**
     * 判断请求是否受限
     *
     * @param request
     * @param requestLimit
     * @return
     */
    public boolean isLimit(HttpServletRequest request, RequestLimit requestLimit) {
        // 受限的redis 缓存key 也可以使用 用户ID 之类的唯一标识
        String ip = IpUtils.getIp(request);
        String limitKey = CacheConstsEnum.RATE_LIMIT.appendKeyByUnderline(ip, request.getSession().getId());

        //从第一次访问设置过期时间
        long currentNumber = redisTemplate.opsForValue().increment(limitKey, 1);
        log.debug("rate limit key is {}, current number is {}", limitKey, currentNumber);

        if (currentNumber == 1) {
            redisTemplate.expire(limitKey, requestLimit.second(), TimeUnit.SECONDS);
        }

        if (currentNumber > requestLimit.maxCount()) {
            log.info("{} request times is {} beyond to LIMIT_NUMBER {}", limitKey, currentNumber, requestLimit.maxCount());
            return false;
        }

        return true;
    }

}