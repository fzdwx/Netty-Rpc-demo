package cn.like.netty.rpc.server.channel;

import cn.hutool.core.util.ObjectUtil;
import cn.like.netty.common.channel.NettyChannelManager;
import cn.like.netty.rpc.server.util.RedisUtil;
import io.netty.channel.Channel;
import org.aopalliance.intercept.Invocation;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static cn.like.netty.common.channel.NettyChannelConstant.*;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Create By like On 2021-04-17 12:22
 * <p>
 * <h2>netty channel 管理器 redis 实现</h2>
 *
 * @Description: redis 实现
 */
@Component
public class NettyChannelManagerRedis implements NettyChannelManager {

    private final static Logger log = getLogger(NettyChannelManagerRedis.class);

    @Autowired
    RedisUtil redis;

    @Override
    public void add(Channel channel) {
        redis.hset(REDIS_HASH_KEY_CHANNELS, channel.id().toString(), channel);
        log.info("#add#一个连接 [{}] 加入#ip: [{}] ", channel.id(), channel.remoteAddress());
    }

    @Override
    public void bindUser(Channel channel, String user) {
        Channel exCh = (Channel) redis.get(channel.id().toString());
        if (ObjectUtil.isNull(exCh)) {
            log.error("#addUser#连接 [{}] 不存在#ip: [{}]", channel.id(), channel.remoteAddress());
            return;
        }
        // 设置属性
        channel.attr(CHANNEL_ATTR_KEY_USER).set(user);
        // 用户和channel 绑定
        redis.hset(REDIS_HASH_KEY_USER_CHANNELS, user, channel);
    }

    @Override
    public void remove(Channel channel) {
        redis.hdel(REDIS_HASH_KEY_CHANNELS, channel.id().toString());
        if (channel.hasAttr(CHANNEL_ATTR_KEY_USER)) {
            String user = channel.attr(CHANNEL_ATTR_KEY_USER).get();
            redis.hdel(REDIS_HASH_KEY_USER_CHANNELS, user);
            log.info("[remove][一个连接({})离开]#user [{}] #ip: [{}]", channel.id(), user, channel.remoteAddress());
        } else {
            log.info("[remove][一个连接({})离开]#ip: [{}]", channel.id(), channel.remoteAddress());
        }
    }

    @Override
    public void send(String user, Invocation invocation) {
        Channel ch = (Channel) redis.hget(REDIS_HASH_KEY_USER_CHANNELS, user);
        if (ObjectUtil.isNull(ch)) {
            log.error("#send#连接不存在#user [{}] ", user);
            return;
        }
        if (!ch.isActive()) {
            log.error("#send#连接 [{}] 未激活#user [{}]", ch.id(), user);
            return;
        }
        // 发送消息
        ch.writeAndFlush(invocation);
    }

    @Override
    public void sendAll(Invocation invocation) {
        redis.hmget(REDIS_HASH_KEY_CHANNELS).values().forEach(o -> {
            Channel ch = (Channel) o;
            if (!ch.isActive()) {
                log.error("#send#连接 [{}] 未激活", ch.id());
                return;
            }
            // 发送消息
            ch.writeAndFlush(invocation);
        });
    }


    @Override
    public Object getAttr(Channel channel, String key) {
        return null;
    }

    @Override
    public void setAttr(Channel channel, String key, Object val) {

    }
}
