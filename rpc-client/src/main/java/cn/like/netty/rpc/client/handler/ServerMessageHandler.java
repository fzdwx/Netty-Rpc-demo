package cn.like.netty.rpc.client.handler;

import cn.like.netty.common.message.Message;
import cn.like.netty.common.message.ServerMessage;
import cn.like.netty.common.message.dispatcher.MessageHandler;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Create By like On 2021-04-18 13:32
 */
@Component
public class ServerMessageHandler implements MessageHandler<ServerMessage> {
    private final static Logger log = getLogger(ServerMessageHandler.class);

    @Override
    public void execute(Channel channel, ServerMessage message) {
        log.info("#channelRead0(..):收到消息 调用{} ", message.getServerMessage());
    }

    @Override
    public int getMessageType() {
        return Message.MessageTypeConstant.ServerMessage;
    }
}
