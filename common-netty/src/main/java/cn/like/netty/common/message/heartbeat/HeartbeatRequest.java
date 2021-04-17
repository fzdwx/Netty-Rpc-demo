package cn.like.netty.common.message.heartbeat;

import cn.like.netty.common.message.Message;
import cn.like.netty.common.util.SeqIdGetter;
import lombok.Data;
import lombok.ToString;

/**
 * Create By like On 2021-04-17 15:37
 * 心跳请求
 */
@Data
@ToString(callSuper = true)
public class HeartbeatRequest extends Message {

    private String message;

    public HeartbeatRequest(String ping) {
        this.message = ping;
        setSequenceId(SeqIdGetter.getId());
    }

    @Override
    public int getMessageType() {
        return MessageTypeConstant.HeartbeatRequest;
    }
}
