package cn.like.netty.common.service;

/**
 * Create By like On 2021-04-18 12:30
 * <p>
 * netty rpc 测试service
 */
public interface HelloService {

    String hello(String name);

    Integer add(Integer a, Integer b);
}
