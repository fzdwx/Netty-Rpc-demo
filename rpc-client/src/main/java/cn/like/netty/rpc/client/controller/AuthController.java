package cn.like.netty.rpc.client.controller;

import cn.like.netty.common.message.auth.AuthRequest;
import cn.like.netty.rpc.client.NettyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By like On 2021-04-17 16:52
 */
@RestController
public class AuthController {

    @Autowired
    NettyClient nettyClient;

    @GetMapping("/auth/{token}")
    public String auth(@PathVariable String token) {
        AuthRequest message = new AuthRequest(token);
        nettyClient.send(message);
        return "ok";
    }
}
