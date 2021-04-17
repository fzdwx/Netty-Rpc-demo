package cn.like.netty.common.message.rpc;

import cn.like.netty.common.message.AbstractResponseMessage;
import cn.like.netty.common.util.SeqIdGetter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * Create By like On 2021-04-14 15:23
 * <p>
 * rpc 响应消息
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class RpcResponse extends AbstractResponseMessage {
    public RpcResponse() {
        this.setSequenceId(SeqIdGetter.getId());
    }

    /** 正常 返回值 */
    private Object returnValue;

    /** 异常值 */
    private Throwable exMessage;

    @Override
    public int getMessageType() {
        return MessageTypeConstant.RpcResponse;
    }
}
