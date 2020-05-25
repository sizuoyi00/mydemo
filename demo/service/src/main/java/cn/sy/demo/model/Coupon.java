package cn.sy.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author guests
 * @since 2020-05-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("coupons_amount")
    private Integer couponsAmount;

    @TableField("min_goods_amount")
    private Integer minGoodsAmount;

    @TableField("create_num")
    private Integer createNum;

    @TableField("use_num")
    private Integer useNum;

    @TableField("received_num")
    private Integer receivedNum;

    @TableField("limit_num")
    private Integer limitNum;

    @TableField("use_start_date")
    private LocalDateTime useStartDate;

    @TableField("use_end_date")
    private LocalDateTime useEndDate;

    @TableField("remark")
    private String remark;


}
