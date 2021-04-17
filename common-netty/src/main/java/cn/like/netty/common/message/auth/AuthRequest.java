package cn.like.netty.common.message.auth;

import cn.like.netty.common.message.Message;
import lombok.Data;

/**
 * Create By like On 2021-04-17 15:50
 * <p>
 * 认证
 */
@Data
public class AuthRequest extends Message {

    /**
     * 认证 Token
     */
    private String accessToken;

    @Override
    public int getMessageType() {
        return MessageTypeConstant.AuthRequest;
    }
}
