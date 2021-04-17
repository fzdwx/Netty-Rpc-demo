package cn.like.netty.rpc.server.handler;

import cn.like.netty.common.message.rpc.RpcRequestMessage;
import cn.like.netty.common.message.rpc.RpcResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

@Component
public class RpcRequestMessageHandler extends SimpleChannelInboundHandler<RpcRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequestMessage msg) {
        final RpcResponseMessage resp = new RpcResponseMessage();

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

        ctx.writeAndFlush(resp);
    }
}
