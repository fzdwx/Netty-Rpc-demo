package cn.like.netty.rpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * Create By like On 2021-04-17 14:25
 */
@SpringBootApplication
@ComponentScan("cn.like.netty.*")
@EnableConfigurationProperties
public class NettyRpcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyRpcClientApplication.class, args);
    }
}
