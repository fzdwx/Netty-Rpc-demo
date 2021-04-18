package cn.like.netty.rpc.server.handler;

import cn.hutool.extra.spring.SpringUtil;
import cn.like.netty.common.message.Message;
import cn.like.netty.common.message.dispatcher.MessageHandler;
import cn.like.netty.common.message.rpc.RpcRequest;
import cn.like.netty.common.message.rpc.RpcResponse;
import cn.like.netty.common.util.ProxyUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class RpcRequestHandler implements MessageHandler<RpcRequest> {

    private final static Logger log = getLogger(RpcRequestHandler.class);

    @Override
    public void execute(Channel channel, RpcRequest message) {
        log.info("[execute][收到连接({}) 的RPC请求][message: {}]", channel.id(), message);
        final RpcResponse resp = new RpcResponse();

        resp.setSequenceId(message.getSequenceId());
        try {  // 反射调用目标方法 invoke
            Object bean = SpringUtil.getBean(Class.forName(message.getInterfaceName()));

            final Method method = bean.getClass()
                    .getMethod(
                            message.getMethodName(),
                            ProxyUtil.StringArrayToClassArray(message.getParameterTypes())
                    );
            final Object res = method.invoke(bean, message.getParameterValue()); // invoke

            resp.setReturnValue(res);
        } catch (Exception e) {  // 异常
            e.printStackTrace();
            resp.setExMessage(new Exception("远程调用出错: " + e.getCause().getMessage()));
        }

        channel.writeAndFlush(resp);
    }

    @Override
    public int getMessageType() {
        return Message.MessageTypeConstant.RpcRequest;
    }
}
