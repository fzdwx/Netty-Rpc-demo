package cn.like.netty.rpc.server.init;

import cn.like.netty.common.protocol.MessageCodecSharable;
import cn.like.netty.rpc.server.config.LikeProtocolFrameDecoder;
import cn.like.netty.rpc.server.handler.NettyServerHandler;
import cn.like.netty.rpc.server.handler.RpcRequestMessageHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create By like On 2021-04-17 11:58
 *
 * @Description: netty 服务器端 handler 初始化
 */
@Component
public class NettyServerHandlerInitializer extends ChannelInitializer<Channel> {

    /** 安全的 like 消息编解码器 */
    @Autowired
    MessageCodecSharable messageCodecSharable;
    /** like 议帧解码器 */
    @Autowired
    LikeProtocolFrameDecoder likeProtocolFrameDecoder;
    /** rpc请求消息处理程序 */
    @Autowired
    RpcRequestMessageHandler rpcRequestMessageHandler;
    /** netty 服务端处理程序 */
    @Autowired
    NettyServerHandler nettyServerHandler;

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline()
                .addLast(new LoggingHandler(LogLevel.DEBUG))
                .addLast(messageCodecSharable)
                .addLast(likeProtocolFrameDecoder)
                .addLast(rpcRequestMessageHandler);
    }
}
