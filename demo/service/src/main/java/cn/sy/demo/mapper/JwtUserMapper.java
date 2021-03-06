package cn.sy.demo.mapper;

import cn.sy.demo.model.JwtUserDo;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guests
 * @since 2019-05-16
 */
@DS("local")
public interface JwtUserMapper extends BaseMapper<JwtUserDo> {

    JwtUserDo selectUserWithRoleByUserName(String username);

    JwtUserDo selectUserAutoWithRoleByUserName(String username);

}
