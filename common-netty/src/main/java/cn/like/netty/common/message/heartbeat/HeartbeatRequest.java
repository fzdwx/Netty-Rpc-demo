package cn.like.netty.common.message.heartbeat;

import cn.like.netty.common.message.Message;

/**
 * Create By like On 2021-04-17 15:37
 * 心跳请求
 */
public class HeartbeatRequest extends Message {

    @Override
    public int getMessageType() {
        return MessageTypeConstant.HeartbeatRequest;
    }
}
