package cn.sy.demo.mapper;

import cn.sy.demo.model.UserInfo;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guests
 * @since 2019-05-03
 */
@DS("master")
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
