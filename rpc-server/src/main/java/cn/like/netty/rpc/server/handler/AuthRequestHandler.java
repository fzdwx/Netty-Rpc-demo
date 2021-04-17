package cn.like.netty.rpc.server.handler;

import cn.hutool.core.util.StrUtil;
import cn.like.netty.common.channel.NettyChannelManager;
import cn.like.netty.common.message.Message;
import cn.like.netty.common.message.auth.AuthRequest;
import cn.like.netty.common.message.auth.AuthResponse;
import cn.like.netty.common.message.dispatcher.MessageHandler;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create By like On 2021-04-17 15:56
 */
@Component
public class AuthRequestHandler implements MessageHandler<AuthRequest> {

    @Autowired
    private NettyChannelManager ncm;

    @Override
    public void execute(Channel channel, AuthRequest message) {
        if (StrUtil.isBlank(message.getAccessToken())) {
            channel.writeAndFlush(AuthResponse.error("accessToken 未传入"));
            return;
        }
        // todo

        // 绑定用户
        ncm.bindUser(channel, message.getAccessToken());
        // 返回成功
        channel.writeAndFlush(AuthResponse.success(message.getAccessToken() + "认证成功"));
    }

    @Override
    public int getMessageType() {
        return Message.MessageTypeConstant.AuthRequest;
    }
}
