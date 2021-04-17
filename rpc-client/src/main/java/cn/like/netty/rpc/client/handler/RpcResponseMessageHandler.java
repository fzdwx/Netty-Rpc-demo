package cn.like.netty.rpc.client.handler;

import cn.like.netty.common.message.Message;
import cn.like.netty.common.message.dispatcher.MessageHandler;
import cn.like.netty.common.message.rpc.RpcResponse;
import io.netty.channel.Channel;
import io.netty.util.concurrent.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create By like On 2021-04-14 15:23
 * <p>
 * rpc 响应消息处理
 */
@Component
public class RpcResponseMessageHandler implements MessageHandler<RpcResponse> {
    /** 序号：接收结果的promise */
    public static final Map<Integer, Promise<Object>> RPC_PROMISES = new ConcurrentHashMap<>();
    private final static Logger log = LoggerFactory.getLogger(RpcResponseMessageHandler.class);

    @Override
    public void execute(Channel channel, RpcResponse message) {
        log.info("#channelRead0(..):rpc 调用{} ", message.getReturnValue());

        // 返回 promise
        Promise<Object> promise = RPC_PROMISES.remove(message.getSequenceId());
        if (promise != null) {
            if (Objects.isNull(message.getExMessage())) {
                promise.setSuccess(message.getReturnValue());
            } else {
                promise.setFailure(message.getExMessage());
            }
        }
    }

    @Override
    public int getMessageType() {
        return Message.MessageTypeConstant.RpcResponse;
    }
}
