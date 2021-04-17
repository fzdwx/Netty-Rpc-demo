package cn.like.netty.common.message.chat;

import cn.like.netty.common.message.AbstractResponseMessage;

/**
 * Create By like On 2021-04-12 21:57
 */
public class RegisterResponseMessage extends AbstractResponseMessage {
    public RegisterResponseMessage(boolean success, String reason) {
        super(success, reason);
    }

    @Override
    public int getMessageType() {
        return RegisterResponseMessage;
    }
}
