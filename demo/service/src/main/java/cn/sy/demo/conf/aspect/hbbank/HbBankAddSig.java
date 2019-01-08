package cn.sy.demo.conf.aspect.hbbank;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface HbBankAddSig {

}
