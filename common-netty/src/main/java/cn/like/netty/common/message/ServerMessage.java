package cn.like.netty.common.message;

import cn.like.netty.common.util.SeqIdGetter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Create By like On 2021-04-13 14:30
 * <p>
 * 服务器消息
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ServerMessage extends Message {

    private String serverMessage;

    public ServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
        this.setSequenceId(SeqIdGetter.getId());
    }

    @Override
    public int getMessageType() {
        return MessageTypeConstant.ServerMessage;
    }
}
