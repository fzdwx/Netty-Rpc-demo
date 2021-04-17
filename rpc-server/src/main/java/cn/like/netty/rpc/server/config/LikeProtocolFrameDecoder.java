package cn.like.netty.rpc.server.config;

import cn.like.netty.common.protocol.MessageCodec;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.springframework.stereotype.Component;

/**
 * like 协议帧解码器
 * </p>
 * 构造参数对应 {@link MessageCodec}
 */
@Component
public class LikeProtocolFrameDecoder extends LengthFieldBasedFrameDecoder {

    public LikeProtocolFrameDecoder() {
        this(1024, 18, 4, 0, 0);
    }

    public LikeProtocolFrameDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }
}