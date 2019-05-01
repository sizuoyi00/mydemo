package cn.sy.demo.service;

import cn.sy.demo.constant.UserInfoVO;
import cn.sy.demo.model.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guests
 * @since 2019-05-01
 */
public interface UserInfoService extends IService<UserInfo> {

    @Transactional(rollbackFor = RuntimeException.class)
    boolean registered(UserInfoVO userInfoVO);

    boolean judgeInvitationCode(String invitationCode);

    boolean registeredSms(String mobile);

    boolean valSmsCode(String mobile, String code);
}
