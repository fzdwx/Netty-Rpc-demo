package cn.like.netty.rpc.server.service;

import cn.like.netty.common.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Create By like On 2021-04-18 12:37
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return " Hello : " + name;
    }

    @Override
    public Integer add(Integer a, Integer b) {
        return a + b;
    }
}
