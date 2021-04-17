package cn.like.netty.common.channel;

import cn.like.netty.common.message.Message;
import io.netty.channel.Channel;

/**
 * Create By like On 2021-04-17 12:16
 *
 * @Description: netty channel 管理器 顶级接口
 */
public interface NettyChannelManager {
    /**
     * 添加 channel
     *
     * @param channel 被绑定的channel
     *                {@link Channel}
     */
    void add(Channel channel);

    /**
     * 绑定用户 到channel
     *
     * @param channel 通道
     * @param user    用户
     */
    void bindUser(Channel channel, String user);

    /**
     * 删除 channel
     *
     * @param channel 哪个channel 要解开绑定
     *                {@link Channel}
     */
    void remove(Channel channel);

    /**
     * 向指定用户发送消息
     *
     * @param user    用户
     * @param message 消息体
     */
    void send(String user, Message message);

    /**
     * 向所有用户发送消息
     *
     * @param message 消息体
     */
    void sendAll(Message message);

    /**
     * 获取属性
     *
     * @param channel 通道
     *                {@link Channel}
     * @param key     关键
     *                {@link String}
     * @return {@link Object}
     */
    Object getAttr(Channel channel, String key);

    /**
     * 设置属性
     *
     * @param channel 通道
     *                {@link Channel}
     * @param key     key
     *                {@link String}
     * @param val     值
     *                {@link Object}
     */
    void setAttr(Channel channel, String key, Object val);
}
