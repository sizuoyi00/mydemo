package cn.sy.demo.service.impl;

import cn.sy.demo.model.UserInfo;
import cn.sy.demo.mapper.UserInfoMapper;
import cn.sy.demo.service.UserInfoService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author guests
 * @since 2019-05-03
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUser(Integer id) {
        return userInfoMapper.selectById(id);
    }

    @Override
    public int updateUserInfo(UserInfo userInfo) {
        userInfo.setState("U");
        final int count = userInfoMapper.update(userInfo, Wrappers.<UserInfo>lambdaUpdate()
                .eq(UserInfo::getLoginId, userInfo.getLoginId())
                .eq(UserInfo::getState, "I"));
        return count;
    }

}
