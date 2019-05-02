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
 * <p>
 * </p>
 *
 * @author guests
 * @since 2019-05-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField("login_id")
    private String loginId;

    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 用户密码
     */
    @TableField("pwd")
    private String pwd;

    /**
     * 生日
     */
    @TableField("birthday")
    private String birthday;

    /**
     * 用户状态
     */
    @TableField("state")
    private String state;

    /**
     * 邀请码
     */
    @TableField("invitation_code")
    private String invitationCode;

    /**
     * 用户简介
     */
    @TableField("user_profile")
    private String userProfile;

    /**
     * 用户头像路径
     */
    @TableField("head_img_url")
    private String headImgUrl;

    /**
     * 用户金币数量
     */
    @TableField("coin_count")
    private Integer coinCount;

    /**
     * 金币crystal卡牌路径
     */
    @TableField("coin_img_url")
    private String coinImgUrl;

    /**
     * 用户积分数量
     */
    @TableField("point_count")
    private Integer pointCount;

    /**
     * 积分ruby卡牌路径
     */
    @TableField("point_img_url")
    private String pointImgUrl;

    /**
     * 修改时间
     */
    @TableField("modify_time")
    private LocalDateTime modifyTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;


}
