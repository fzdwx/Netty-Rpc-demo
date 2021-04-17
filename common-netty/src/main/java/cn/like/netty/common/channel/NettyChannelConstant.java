package cn.like.netty.common.channel;

import io.netty.util.AttributeKey;

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
}
