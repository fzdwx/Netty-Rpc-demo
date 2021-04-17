package cn.like.netty.rpc.client.handler;

import cn.like.netty.common.message.Message;
import cn.like.netty.common.message.auth.AuthResponse;
import cn.like.netty.common.message.dispatcher.MessageHandler;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Create By like On 2021-04-17 16:19
 */
@Component
public class AuthResponseHandler implements MessageHandler<AuthResponse> {
    private final static Logger log = getLogger(AuthResponseHandler.class);

    @Override
    public void execute(Channel channel, AuthResponse message) {
        log.info("[execute][认证结果：{}]", message);
    }

    @Override
    public int getMessageType() {
        return Message.MessageTypeConstant.AuthResponse;
    }
}
