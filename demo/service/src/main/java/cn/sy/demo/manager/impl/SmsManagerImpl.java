package cn.sy.demo.manager.impl;

import cn.sy.demo.constant.SmsSendVO;
import cn.sy.demo.constant.enums.CacheConstsEnum;
import cn.sy.demo.manager.SmsManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 没有加业务场景，即一个手机号只可以再一个场景下验证
 */
@Component
@Slf4j
public class SmsManagerImpl implements SmsManager {

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void sendVerificationCode(String mobile) {

        String code = "123456";
        if(!"dev".equals(profile)){
            code = (int)((Math.random()*9+1)*100000)+"";
            //TODO 发送短信
        }

        redisTemplate.opsForValue().set(getKeyByMobile(mobile),code);
        log.debug("已发送短信:短信内容为:" + code + "手机号:" + mobile);
    }

    @Override
    public void sendMessage(SmsSendVO smsSendVO) {

    }

    @Override
    public boolean valid( String mobile, String code) {
        //redis中获取验证码
        String valCode = redisTemplate.opsForValue().get(getKeyByMobile(mobile));
        if(code.equals(valCode)){
            return true;
        }

        return false;
    }

    private String getKeyByMobile(String mobile) {
        return CacheConstsEnum.SEND_SMS_MOBILE_KEY.appendKeyByUnderline(mobile);
    }

}
