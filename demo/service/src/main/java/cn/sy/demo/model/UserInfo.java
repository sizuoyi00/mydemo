package cn.sy.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author guests
 * @since 2019-05-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String loginId;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户密码
     */
    private String pwd;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 用户状态
     */
    private String state;

    /**
     * 邀请码
     */
    private String invitationCode;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户头像路径
     */
    private String headImgUrl;

    /**
     * 用户金币数量
     */
    private Integer coinCount;

    /**
     * 金币crystal卡牌路径
     */
    private String coinImgUrl;

    /**
     * 用户积分数量
     */
    private Integer pointCount;

    /**
     * 积分ruby卡牌路径
     */
    private String pointImgUrl;

    /**
     * 修改时间
     */
    @Version
    private LocalDateTime modifyTime;


}
