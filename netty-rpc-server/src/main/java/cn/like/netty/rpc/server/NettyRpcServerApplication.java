package cn.like.netty.rpc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("cn.like.netty.*")
public class NettyRpcServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyRpcServerApplication.class, args);
    }

}
