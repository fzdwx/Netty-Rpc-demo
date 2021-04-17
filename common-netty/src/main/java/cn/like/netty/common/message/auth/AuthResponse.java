package cn.like.netty.common.message.auth;

import cn.like.netty.common.message.AbstractResponseMessage;
import cn.like.netty.common.util.SeqIdGetter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Create By like On 2021-04-17 15:53
 * <p>
 * 认证
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class AuthResponse extends AbstractResponseMessage {
    /** 响应码 */
    private Integer code;
    /** 响应消息 */
    private String message;

    public AuthResponse(int code, String message) {
        this.code = code;
        this.message = message;
        setSequenceId(SeqIdGetter.getId());
    }

    public static AuthResponse error(String message) {
        return error(AuthCode.error.code, message);
    }

    private static AuthResponse error(int code, String message) {
        return new AuthResponse(code, message);
    }

    public static AuthResponse success() {
        return success("");
    }

    public static AuthResponse success(String message) {
        return success(AuthCode.success.code, message);
    }

    private static AuthResponse success(int code, String message) {
        return new AuthResponse(code, message);
    }

    @Override
    public int getMessageType() {
        return MessageTypeConstant.AuthResponse;
    }

    enum AuthCode {
        success(1),
        error(0);

        private final int code;

        AuthCode(int i) {
            this.code = i;
        }
    }
}
