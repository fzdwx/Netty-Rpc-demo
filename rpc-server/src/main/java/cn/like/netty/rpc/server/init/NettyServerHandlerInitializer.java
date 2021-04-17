package cn.like.netty.rpc.server.init;

import cn.like.netty.common.message.dispatcher.MessageDispatcher;
import cn.like.netty.common.protocol.MessageCodecSharable;
import cn.like.netty.rpc.server.handler.NettyServerHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Create By like On 2021-04-17 11:58
 *
 * @Description: netty 服务器端 handler 初始化
 */
@Component
public class NettyServerHandlerInitializer extends ChannelInitializer<Channel> {

    /**
     * 心跳超时时间
     */
    private static final Integer READ_TIMEOUT_SECONDS = 3 * 60;

    @Autowired
    MessageCodecSharable messageCodecSharable;
    @Autowired
    NettyServerHandler nettyServerHandler;
    @Autowired
    MessageDispatcher messageDispatcher;

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline()
                // 空闲检测
                .addLast(new ReadTimeoutHandler(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS))
                .addLast(new LoggingHandler(LogLevel.DEBUG))
                .addLast(new LengthFieldBasedFrameDecoder(1024, 18, 4, 0, 0))
                .addLast(messageCodecSharable)
                .addLast(nettyServerHandler)
                .addLast(messageDispatcher);
    }
}
