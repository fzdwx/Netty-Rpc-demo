package cn.like.netty.rpc.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Create By like On 2021-04-17 13:09
 * <p>
 * netty Rpc 服务端
 */
@Configuration
@ConfigurationProperties(prefix = "netty")
public class NettyRpcServerConfig {

    private Integer serverPort;

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }
}
