package cn.like.netty.rpc.client.controller;

import cn.like.netty.common.service.HelloService;
import cn.like.netty.rpc.client.proxy.RpcProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By like On 2021-04-18 12:38
 */
@RestController
public class HelloController {

    @Autowired
    RpcProxy rpcProxy;


    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        HelloService helloService = rpcProxy.getProxyService(HelloService.class);
        return helloService.hello(name);
    }

    @GetMapping("/add/{a}/{b}")
    public String add(@PathVariable Integer a, @PathVariable Integer b) {
        HelloService helloService = rpcProxy.getProxyService(HelloService.class);
        int add = helloService.add(a, b);
        return String.valueOf(add);
    }
}
