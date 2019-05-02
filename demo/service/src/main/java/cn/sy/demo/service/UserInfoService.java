package cn.sy.demo.service;

import cn.sy.demo.model.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guests
 * @since 2019-05-03
 */
public interface UserInfoService extends IService<UserInfo> {

    UserInfo getUser(Integer id);

    int updateUserInfo(UserInfo userInfo);
}
