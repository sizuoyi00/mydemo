package cn.sy.demo.conf.aspect.hbbank;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
@Inherited // 一个子类想获取到父类上的注解信息，必须在父类上使用的注解加上@Inherit关键字
@Retention(RetentionPolicy.RUNTIME)
public @interface HbBankAddSig {

}
