package cn.like.netty.rpc.server.handler;

import cn.like.netty.common.channel.NettyChannelManager;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Create By like On 2021-04-17 12:13
 *
 * @Description: netty 服务端处理器
 */
@Component
@ChannelHandler.Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private final static Logger log = getLogger(NettyServerHandler.class);

    @Autowired
    private NettyChannelManager chm;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 保存channel 连接
        chm.add(ctx.channel());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        // 移除channel 连接
        chm.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("#exceptionCaught#连接 [{}] 发生异常", ctx.channel().id(), cause);
        // 断开连接
        ctx.channel().close();
    }
}
