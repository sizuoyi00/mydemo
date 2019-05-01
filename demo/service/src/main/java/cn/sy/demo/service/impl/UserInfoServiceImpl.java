package cn.sy.demo.service.impl;

import cn.sy.demo.constant.UserInfoVO;
import cn.sy.demo.constant.enums.InvitationCodeEnum;
import cn.sy.demo.constant.exception.BusinessException;
import cn.sy.demo.dao.tarot.InvitationInfoMapper;
import cn.sy.demo.dao.tarot.UserInfoMapper;
import cn.sy.demo.manager.SmsManager;
import cn.sy.demo.model.InvitationInfo;
import cn.sy.demo.model.UserInfo;
import cn.sy.demo.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author guests
 * @since 2019-05-01
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Autowired
    private InvitationInfoMapper invitationInfoMapper;

    @Autowired
    private SmsManager smsManager;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean registered(UserInfoVO userInfoVO) {
        UserInfo userInfo = UserInfoVo2Do(userInfoVO);
        try {
            userInfoMapper.insert(userInfo);
        } catch (DuplicateKeyException e) {
            throw new BusinessException("该用户名已经被注册");
        }

        //修改该邀请码状态由初始状态变为已使用
        final int count = invitationInfoMapper.update(
                new InvitationInfo().setState(InvitationCodeEnum.USED.getCode()),
                Wrappers.<InvitationInfo>lambdaUpdate()
                        .eq(InvitationInfo::getInvitationCode, userInfoVO.getInvitationCode())
                        .eq(InvitationInfo::getState, InvitationCodeEnum.INIT.getCode())
        );

        Assert.isTrue(count > 0, "邀请码不存在或已经被使用");
        return true;
    }

    @Override
    public boolean judgeInvitationCode(String invitationCode) {

        invitationInfoMapper.selectCount(
                Wrappers.<InvitationInfo>lambdaQuery()
                        .select(InvitationInfo::getInvitationCode));


        final Integer count = invitationInfoMapper.selectCount(new QueryWrapper<InvitationInfo>().lambda()
                .eq(InvitationInfo::getInvitationCode, invitationCode)
                .eq(InvitationInfo::getState, InvitationCodeEnum.INIT.getCode()));


        return count > 0 ? true : false;
    }

    @Override
    public boolean registeredSms(String mobile) {
        smsManager.sendVerificationCode(mobile);
        return true;
    }

    @Override
    public boolean valSmsCode(String mobile, String code) {
        return smsManager.valid(mobile, code);
    }

    private UserInfo UserInfoVo2Do(UserInfoVO userInfoVO) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoVO, userInfo);
        return userInfo;
    }
}
