package cn.like.netty.common.message.auth;

import cn.like.netty.common.message.Message;
import cn.like.netty.common.util.SeqIdGetter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Create By like On 2021-04-17 15:50
 * <p>
 * 认证
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class AuthRequest extends Message {

    /**
     * 认证 Token
     */
    private String accessToken;

    public AuthRequest(String token) {
        this.accessToken = token;
        this.setSequenceId(SeqIdGetter.getId());
    }


    @Override
    public int getMessageType() {
        return MessageTypeConstant.AuthRequest;
    }
}
