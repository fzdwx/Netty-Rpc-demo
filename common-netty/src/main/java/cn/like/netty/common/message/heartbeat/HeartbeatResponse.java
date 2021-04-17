package cn.like.netty.common.message.heartbeat;

import cn.like.netty.common.message.AbstractResponseMessage;
import cn.like.netty.common.util.SeqIdGetter;
import lombok.Data;
import lombok.ToString;

/**
 * Create By like On 2021-04-17 15:55
 */
@Data
@ToString(callSuper = true)
public class HeartbeatResponse extends AbstractResponseMessage {

    public HeartbeatResponse(String message) {
        this.setSequenceId(SeqIdGetter.getId());
        this.setReason(message);
    }

    @Override
    public int getMessageType() {
        return MessageTypeConstant.HeartbeatResponse;
    }
}
