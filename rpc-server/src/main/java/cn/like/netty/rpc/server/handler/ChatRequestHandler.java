package cn.like.netty.rpc.server.handler;

import cn.like.netty.common.channel.NettyChannelConstant;
import cn.like.netty.common.message.Message;
import cn.like.netty.common.message.chat.ChatMessage;
import cn.like.netty.common.message.dispatcher.MessageHandler;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Create By like On 2021-04-18 13:32
 */
@Component
public class ChatRequestHandler implements MessageHandler<ChatMessage> {
    private final static Logger log = getLogger(ChatRequestHandler.class);

    @Override
    public void execute(Channel channel, ChatMessage message) {
        message.setFrom(channel.attr(NettyChannelConstant.CHANNEL_ATTR_KEY_USER).get());
        Channel to = NettyChannelConstant.userChannels.get(message.getTo());
        to.writeAndFlush(message);
        log.info("[execute][转发chat消息 {} ] ", message);
    }

    @Override
    public int getMessageType() {
        return Message.MessageTypeConstant.ChatMessage;
    }
}
