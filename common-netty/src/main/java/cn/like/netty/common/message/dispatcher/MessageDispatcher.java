package cn.like.netty.common.message.dispatcher;

import cn.like.netty.common.message.Message;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create By like On 2021-04-17 15:16
 * <p>
 * message 分发器
 */
@ChannelHandler.Sharable
public class MessageDispatcher extends SimpleChannelInboundHandler<Message> {

    private final ExecutorService executor = Executors.newFixedThreadPool(200);
    @Autowired
    private MessageContainer messageHandlerContainer;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        MessageHandler handler = messageHandlerContainer.getMessageHandler(message.getMessageType());

        executor.submit(() -> {
            handler.execute(channelHandlerContext.channel(), message);
        });

    }
}
