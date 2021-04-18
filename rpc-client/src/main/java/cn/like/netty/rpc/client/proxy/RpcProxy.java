package cn.like.netty.rpc.client.proxy;

import cn.like.netty.common.message.rpc.RpcRequest;
import cn.like.netty.common.util.ProxyUtil;
import cn.like.netty.common.util.SeqIdGetter;
import cn.like.netty.rpc.client.NettyClient;
import cn.like.netty.rpc.client.handler.RpcResponseHandler;
import io.netty.util.concurrent.DefaultPromise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicReference;


/**
 * Create By like On 2021-04-18 12:32
 */
@Component
public class RpcProxy {

    @Autowired
    NettyClient nettyClient;

    /**
     * 代理服务，调用目标方法
     *
     * @param serviceClass 服务类
     * @return {@link T}
     */
    public <T> T getProxyService(Class<T> serviceClass) {
        Object o = Proxy.newProxyInstance(
                serviceClass.getClassLoader(),   // 类加载器
                new Class[]{serviceClass},      // 实例化的接口
                getInvocationHandler(serviceClass));
        return (T) o;
    }


    /**
     * invocation method
     *
     * @param serviceClass 服务类
     * @return {@link InvocationHandler}
     */
    private <T> InvocationHandler getInvocationHandler(Class<T> serviceClass) {
        return (proxy, method, args) -> {
            // 1.将方法调用转换为消息对象
            int sqeId = SeqIdGetter.getId();
            RpcRequest resp = getRpcRequestMessage(serviceClass, method, args, sqeId);
            // 2.发送消息
            nettyClient.getChannel().writeAndFlush(resp);

            // 3.准备一个promise对象,接收结果                promise 运行的线程
            DefaultPromise<Object> promise = new DefaultPromise<>(nettyClient.getChannel().eventLoop());
            RpcResponseHandler.RPC_PROMISES.put(sqeId, promise);
            AtomicReference<Object> now = new AtomicReference<>();
                   /* promise.addListener(future -> {
                        future.sync();
                        if (future.isSuccess()) {
                            now.set(future.getNow());

                        } // 异常
                        else
                            throw new RuntimeException(future.cause());
                    });*/
            promise.sync();
            if (promise.isSuccess()) {
                now.set(promise.getNow());
            } // 异常
            else
                throw new RuntimeException(promise.cause());
            // 4. 同步返回结果
            return now.get();
        };
    }

    /**
     * 封装成 rpc请求消息
     *
     * @param serviceClass 服务类
     * @param method       方法
     * @param args         arg游戏
     * @param sqeId        sqe id
     * @return {@link RpcRequest}
     */
    private <T> RpcRequest getRpcRequestMessage(Class<T> serviceClass, java.lang.reflect.Method method, Object[] args, int sqeId) {

        return new RpcRequest(
                sqeId,
                serviceClass.getName(),
                method.getName(),
                method.getReturnType().getName(),
                ProxyUtil.classArrayToClassStringArray(method.getParameterTypes()),
                args
        );
    }
}
