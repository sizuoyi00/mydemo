package cn.sy.demo.mapper;

import cn.sy.demo.model.User;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户基础信息 Mapper 接口
 * </p>
 *
 * @author guests
 * @since 2019-05-03
 */
@DS("local")
public interface UserMapper extends BaseMapper<User> {

    /**
     * 方式1，在这里使用@Select之类的注解写sql
     *       @Select("select * from user limit 1")
     * 方式2，已经绑定了对应的xml，在xml写sql
     * @return
     * @param idCard
     */
//    @Select("select * from user limit 1")
    User getUser(String idCard);

}
