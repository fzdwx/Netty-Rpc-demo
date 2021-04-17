package cn.like.netty.rpc.server.config;

import cn.like.netty.common.message.dispatcher.MessageContainer;
import cn.like.netty.common.message.dispatcher.MessageDispatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyServerConfig {

    @Bean
    public MessageDispatcher messageDispatcher() {
        return new MessageDispatcher();
    }

    @Bean
    public MessageContainer messageHandlerContainer() {
        return new MessageContainer();
    }

}