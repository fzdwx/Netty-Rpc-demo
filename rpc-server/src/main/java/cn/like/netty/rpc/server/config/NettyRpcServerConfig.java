package cn.like.netty.rpc.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Create By like On 2021-04-17 13:09
 * <p>
 * netty Rpc 服务端
 */
@Configuration
@ConfigurationProperties(prefix = "netty")
@Data
public class NettyRpcServerConfig {

    private Integer serverPort;
}
