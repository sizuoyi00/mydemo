package cn.sy.demo.manager;

import cn.sy.demo.constant.SmsSendVO;

/**
 * 手机短信接口
 *
 * @author zh
 * @version v2.0
 * @since v7.0.0
 * 2018年3月19日 下午4:02:40
 */
public interface SmsManager {
    /**
     * 发送手机验证码
     *
     * @param mobile
     */
    void sendVerificationCode(String mobile);

    /**
     * 发送手机指定信息
     * @param smsSendVO
     */
    void sendMessage(SmsSendVO smsSendVO);

    /**
     * 验证手机验证码
     * @param mobile 手机号码
     * @param code   手机验证码
     * @return 是否通过校验 true通过，false不通过
     */
    boolean valid(String mobile, String code);

}
