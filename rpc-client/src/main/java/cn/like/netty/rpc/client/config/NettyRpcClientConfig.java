package cn.like.netty.rpc.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "netty")
@Data
public class NettyRpcClientConfig {

    public Integer serverPort;
    public String serverHost;
}
