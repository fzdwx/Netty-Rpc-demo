package cn.like.netty.rpc.client;

import cn.like.netty.common.message.Message;
import cn.like.netty.rpc.client.config.NettyRpcClientConfig;
import cn.like.netty.rpc.client.init.NettyClientHandlerInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Create By like On 2021-04-17 14:12
 * <p>
 * netty 客户端
 */
@Component
public class NettyClient {

    private final static Logger log = getLogger(NettyClient.class);
    /** 重新连接间隔 */
    private static final Integer RECONNECT_SECONDS = 20;
    /**
     * 线程组，用于客户端对服务端的连接、数据读写
     */
    private EventLoopGroup eventGroup = new NioEventLoopGroup();
    /**
     * Netty Client Channel
     */
    private volatile Channel channel;

    @Autowired
    private NettyClientHandlerInitializer nettyClientHandlerInitializer;
    @Autowired
    private NettyRpcClientConfig nettyRpcClientConfig;

    /**
     * 启动 Netty Server
     */
    @PostConstruct
    public void start() throws InterruptedException {
        // <2.1> 创建 Bootstrap 对象，用于 Netty Client 启动
        Bootstrap bootstrap = new Bootstrap();
        // <2.2>
        bootstrap.group(eventGroup) // <2.2.1> 设置一个 EventLoopGroup 对象
                .channel(NioSocketChannel.class)  // <2.2.2> 指定 Channel 为客户端 NioSocketChannel
                .remoteAddress(nettyRpcClientConfig.getServerHost(), nettyRpcClientConfig.getServerPort()) // <2.2.3> 指定连接服务器的地址
                .option(ChannelOption.SO_KEEPALIVE, true) // <2.2.4> TCP Keepalive 机制，实现 TCP 层级的心跳保活功能
                .option(ChannelOption.TCP_NODELAY, true) //<2.2.5>  允许较小的数据包的发送，降低延迟
                .handler(nettyClientHandlerInitializer);
        // <2.3> 连接服务器，并异步等待成功，即启动客户端
        bootstrap.connect().addListener(new ChannelFutureListener() {

            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                // 连接失败
                if (!future.isSuccess()) {
                    log.error("[start][Netty Client 连接服务器({}:{}) 失败]", nettyRpcClientConfig.getServerHost(), nettyRpcClientConfig.getServerPort());
                    reconnect();
                    return;
                }
                // 连接成功
                channel = future.channel();
                log.info("[start][Netty Client 连接服务器({}:{}) 成功]", nettyRpcClientConfig.getServerHost(), nettyRpcClientConfig.getServerPort());
            }
        });
    }

    /**
     * 关闭 Netty Server
     */
    @PreDestroy
    public void shutdown() {
        // <3.1> 关闭 Netty Client
        if (channel != null) {
            channel.close();
        }
        // <3.2> 优雅关闭一个 EventLoopGroup 对象
        eventGroup.shutdownGracefully();
    }

    public void reconnect() {
        eventGroup.schedule(new Runnable() {
            @Override
            public void run() {
                log.info("[reconnect][开始重连]");
                try {
                    start();
                } catch (InterruptedException e) {
                    log.error("[reconnect][重连失败]", e);
                }
            }
        }, RECONNECT_SECONDS, TimeUnit.SECONDS);
        log.info("[reconnect][{} 秒后将发起重连]", RECONNECT_SECONDS);
    }


    /**
     * 发送消息
     *
     * @param message 消息体
     * @return
     */
    public void send(Message message) {
        if (channel == null) {
            log.error("[send][连接不存在]");
            return;
        }
        if (!channel.isActive()) {
            log.error("[send][连接({})未激活]", channel.id());
            return;
        }
        // 发送消息
        System.out.println(message);
        channel.writeAndFlush(message);
    }

    /**
     * get channel
     *
     * @return {@link Channel}
     */
    public Channel getChannel() {
        return channel;
    }
}
