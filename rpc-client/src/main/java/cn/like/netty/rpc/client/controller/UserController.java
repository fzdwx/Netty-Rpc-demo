package cn.like.netty.rpc.client.controller;

import cn.like.netty.common.message.chat.ChatMessage;
import cn.like.netty.common.service.UserService;
import cn.like.netty.rpc.client.NettyClient;
import cn.like.netty.rpc.client.proxy.RpcProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By like On 2021-04-18 13:17
 */
@RestController
public class UserController {

    @Autowired
    RpcProxy rpcProxy;
    @Autowired
    NettyClient nettyClient;

    @GetMapping("user/list")
    public String list() {
        return rpcProxy.getProxyService(UserService.class).list();
    }

    @GetMapping("user/send/{to}/{message}")
    public String send(@PathVariable String to, @PathVariable String message) {
        nettyClient.send(new ChatMessage(null, to, message));
        return "success";
    }

}
