package cn.like.netty.rpc.server.handler;

import cn.like.netty.common.message.Message;
import cn.like.netty.common.message.dispatcher.MessageHandler;
import cn.like.netty.common.message.heartbeat.HeartbeatRequest;
import cn.like.netty.common.message.heartbeat.HeartbeatResponse;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Create By like On 2021-04-17 15:36
 * <p>
 * 心跳响应
 */
@Component
public class HeartbeatRequestHandler implements MessageHandler<HeartbeatRequest> {
    private final static Logger log = getLogger(HeartbeatRequestHandler.class);

    @Override
    public void execute(Channel channel, HeartbeatRequest message) {
        log.info("[execute][收到连接({}) 的心跳请求]", channel.id());
        // 响应心跳
        HeartbeatResponse response = new HeartbeatResponse();
        channel.writeAndFlush(response);
    }

    @Override
    public int getMessageType() {
        return Message.MessageTypeConstant.HeartbeatRequest;
    }
}
