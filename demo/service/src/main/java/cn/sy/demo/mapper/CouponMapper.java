package cn.sy.demo.mapper;

import cn.sy.demo.model.Coupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author guests
 * @since 2020-05-17
 */
public interface CouponMapper extends BaseMapper<Coupon> {

    @Update("update coupon set received_num = received_num + 1 where id = #{couponId} and received_num < create_num")
    long updateReceiveNum(int couponId);
}
