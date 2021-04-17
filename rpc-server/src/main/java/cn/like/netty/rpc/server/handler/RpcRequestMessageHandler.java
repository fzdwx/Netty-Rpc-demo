package cn.like.netty.rpc.server.handler;

import cn.like.netty.common.message.Message;
import cn.like.netty.common.message.dispatcher.MessageHandler;
import cn.like.netty.common.message.rpc.RpcRequest;
import cn.like.netty.common.message.rpc.RpcResponse;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

@Component
public class RpcRequestMessageHandler implements MessageHandler<RpcRequest> {


    @Override
    public void execute(Channel channel, RpcRequest message) {
        final RpcResponse resp = new RpcResponse();

        // resp.setSequenceId(msg.getSequenceId());
        // try {  // 反射调用目标方法 invoke
        //     Object impl = ServicesFactory.getService(Class.forName(msg.getInterfaceName()));
        //     final Method method = impl.getClass().getMethod(msg.getMethodName(), ClassUtil.getClasses(msg.getParameterTypes()));
        //     final Object res = method.invoke(impl, msg.getParameterValue()); // invoke
        //
        //     resp.setReturnValue(res);
        // } catch (Exception e) {  // 异常
        //     e.printStackTrace();
        //     resp.setExMessage(new Exception("远程调用出错: " + e.getCause().getMessage()));
        // }

        channel.writeAndFlush(resp);
    }

    @Override
    public int getMessageType() {
        return Message.MessageTypeConstant.RpcRequest;
    }
}
