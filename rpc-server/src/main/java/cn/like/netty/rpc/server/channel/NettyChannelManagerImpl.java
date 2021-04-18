package cn.like.netty.rpc.server.channel;

import cn.like.netty.common.channel.NettyChannelConstant;
import cn.like.netty.common.channel.NettyChannelManager;
import cn.like.netty.common.message.Message;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import static cn.like.netty.common.channel.NettyChannelConstant.CHANNEL_ATTR_KEY_USER;
import static cn.like.netty.common.channel.NettyChannelConstant.userChannels;
import static org.slf4j.LoggerFactory.getLogger;

@Component
public class NettyChannelManagerImpl implements NettyChannelManager {
    private final static Logger log = getLogger(NettyChannelManagerImpl.class);

    /**
     * 添加 Channel 到 {@link NettyChannelConstant#channels} 中
     *
     * @param channel Channel
     */
    public void add(Channel channel) {
        NettyChannelConstant.channels.put(channel.id(), channel);
        log.info("[add][一个连接({})加入]", channel.id());
    }

    @Override
    public void bindUser(Channel channel, String user) {
        Channel existChannel = NettyChannelConstant.channels.get(channel.id());
        if (existChannel == null) {
            log.error("[addUser][连接({}) 不存在]", channel.id());
            return;
        }
        // 添加到 userChannels
        if (!userChannels.containsValue(channel)) {
            // 设置属性
            channel.attr(CHANNEL_ATTR_KEY_USER).set(user);
            NettyChannelConstant.userChannels.put(user, channel);
        }
    }

    /**
     * 将 Channel 从 {@link NettyChannelConstant#channels} 和 {@link NettyChannelConstant#userChannels} 中移除
     *
     * @param channel Channel
     */
    @Override
    public void remove(Channel channel) {
        // 移除 channels
        NettyChannelConstant.channels.remove(channel.id());
        // 移除 userChannels
        String name = "";
        if (channel.hasAttr(CHANNEL_ATTR_KEY_USER)) {
            channel.attr(CHANNEL_ATTR_KEY_USER).get();
            NettyChannelConstant.userChannels.remove(name);
        }
        log.info("[remove][一个连接({})离开][name {}]", channel.id(), name);
    }

    /**
     * 向指定用户发送消息
     *
     * @param user    用户
     * @param message 消息体
     */
    @Override
    public void send(String user, Message message) {
        // 获得用户对应的 Channel
        Channel channel = NettyChannelConstant.userChannels.get(user);
        if (channel == null) {
            log.error("[send][连接不存在]");
            return;
        }
        if (!channel.isActive()) {
            log.error("[send][连接({})未激活]", channel.id());
            return;
        }
        // 发送消息
        channel.writeAndFlush(message);
    }

    /**
     * 向所有用户发送消息
     *
     * @param message 消息体
     */
    @Override
    public void sendAll(Message message) {
        for (Channel channel : NettyChannelConstant.channels.values()) {
            if (!channel.isActive()) {
                log.error("[send][连接({})未激活]", channel.id());
                return;
            }
            // 发送消息
            channel.writeAndFlush(message);
        }
    }

    @Override
    public Object getAttr(Channel channel, String key) {
        return null;
    }

    @Override
    public void setAttr(Channel channel, String key, Object val) {

    }
}