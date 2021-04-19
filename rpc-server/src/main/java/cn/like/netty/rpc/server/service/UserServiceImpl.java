package cn.like.netty.rpc.server.service;

import cn.hutool.json.JSONUtil;
import cn.like.netty.common.service.UserService;
import org.springframework.stereotype.Component;

import static cn.like.netty.common.channel.NettyChannelConstant.userChannels;

/**
 * Create By like On 2021-04-18 13:15
 */
@Component
public class UserServiceImpl implements UserService {

    @Override
    public String list() {
        return JSONUtil.toJsonStr(userChannels.keySet());
    }
}
