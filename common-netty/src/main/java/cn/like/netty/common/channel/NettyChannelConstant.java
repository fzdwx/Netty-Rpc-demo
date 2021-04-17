package cn.like.netty.common.channel;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.util.AttributeKey;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Create By like On 2021-04-17 12:57
 * <p>
 * netty channel 常量
 */
public class NettyChannelConstant {
    public static final AttributeKey<String> CHANNEL_ATTR_KEY_USER = AttributeKey.newInstance("user");
    /** channel 映射  channel.id().toString() : channel */
    public static final String REDIS_HASH_KEY_CHANNELS = "channels";
    /** 用户 和 channel 的映射  user : channel */
    public static final String REDIS_HASH_KEY_USER_CHANNELS = "channels";


    /**
     * Channel 映射
     */
    public static ConcurrentMap<ChannelId, Channel> channels = new ConcurrentHashMap<>();
    /**
     * 用户与 Channel 的映射。
     * <p>
     * 通过它，可以获取用户对应的 Channel。这样，我们可以向指定用户发送消息。
     */
    public static ConcurrentMap<String, Channel> userChannels = new ConcurrentHashMap<>();
}
