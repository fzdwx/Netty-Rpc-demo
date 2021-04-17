package cn.like.netty.rpc.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("cn.like.netty.*")
public class NettyRpcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyRpcClientApplication.class, args);
    }

}
