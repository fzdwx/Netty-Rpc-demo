package cn.like.netty.rpc.client.init;

import cn.like.netty.common.message.dispatcher.MessageDispatcher;
import cn.like.netty.common.protocol.MessageCodecSharable;
import cn.like.netty.rpc.client.handler.NettyClientHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create By like On 2021-04-17 14:35
 *
 * @Description: netty 客户端 handler 初始化
 */
@Component
public class NettyClientHandlerInitializer extends ChannelInitializer<Channel> {

    /**
     * 心跳超时时间
     */
    private static final Integer READ_TIMEOUT_SECONDS = 60;

    @Autowired
    MessageCodecSharable messageCodecSharable;
    @Autowired
    NettyClientHandler nettyClientHandler;
    @Autowired
    MessageDispatcher messageDispatcher;

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline()
                .addLast(new IdleStateHandler(READ_TIMEOUT_SECONDS, 0, 0))
                .addLast(new ReadTimeoutHandler(3 * READ_TIMEOUT_SECONDS))
                .addLast(new LoggingHandler(LogLevel.DEBUG))
                .addLast(new LengthFieldBasedFrameDecoder(1024, 18, 4, 0, 0))
                .addLast(messageCodecSharable)
                .addLast(nettyClientHandler)
                .addLast(messageDispatcher);


    }
}
