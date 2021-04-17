package cn.like.netty.common.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Create By like On 2021-04-13 14:30
 * <p>
 * 服务器消息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServerMessage extends Message {

    private String serverMessage;

    public ServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    @Override
    public int getMessageType() {
        return ServerMessage;
    }
}
