package cn.sy.demo.conf.interceptor.res;

import java.lang.annotation.*;

/**
 * 此注解用于Controller中返回json数据的方法。<br/>
 * 当方法标注了此注解时，表明返回的数据将不再被ApiResponseAspect拦截并包装成如下格式:
 * <pre>
 * {
 *     code: 0
 *     message: 'xxx'
 *     data: {...}
 * }
 * </pre>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SkipResponseWrap {

}
