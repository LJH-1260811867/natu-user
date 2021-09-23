package edu.natu.systemuser.common.token;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Ljiahai
 * @des 用户信息类，将会通过加密存放在token中
 * @date 2021-09-17 16:07:46
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserToken {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户登录账户
     */
    private String account;

    /**
     * 到期时间
     */
    private Date expiresAt;
}
