package cn.like.netty.rpc.client.handler;

import cn.like.netty.common.message.Message;
import cn.like.netty.common.message.dispatcher.MessageHandler;
import cn.like.netty.common.message.heartbeat.HeartbeatResponse;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Create By like On 2021-04-17 18:20
 */
@Component
public class HeartbeatResponseHandler implements MessageHandler<HeartbeatResponse> {

    private final static Logger log = getLogger(HeartbeatResponseHandler.class);

    @Override
    public void execute(Channel channel, HeartbeatResponse message) {
        log.info("[execute][心跳响应：{}]", message);
    }

    @Override
    public int getMessageType() {
        return Message.MessageTypeConstant.HeartbeatResponse;
    }
}
