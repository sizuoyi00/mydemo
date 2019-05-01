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
public class InvitationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 邀请码
     */
    private String invitationCode;

    /**
     * 邀请码状态
     */
    private String state;

    @Version
    private LocalDateTime modifyTime;


}
