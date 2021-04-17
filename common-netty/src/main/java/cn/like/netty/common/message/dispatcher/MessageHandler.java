package cn.like.netty.common.message.dispatcher;

import cn.like.netty.common.message.Message;
import io.netty.channel.Channel;


/**
 * Create By like On 2021-04-17 15:00
 * <p>
 * 消息处理器 顶级接口
 */
public interface MessageHandler<T extends Message> {

    /**
     * 执行处理消息
     *
     * @param channel 通道
     * @param message 消息
     */
    void execute(Channel channel, T message);

    /**
     * 得到消息类型
     *
     * @return int
     * @see Message.MessageTypeConstant
     */
    int getMessageType();
}
