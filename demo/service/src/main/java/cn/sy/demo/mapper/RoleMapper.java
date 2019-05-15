package cn.sy.demo.mapper;

import cn.sy.demo.model.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guests
 * @since 2019-05-15
 */
public interface RoleMapper extends BaseMapper<Role> {

    Role selectrole(int id);

}
