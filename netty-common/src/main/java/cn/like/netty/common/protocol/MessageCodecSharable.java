package cn.like.netty.common.protocol;

import cn.like.netty.common.message.Message;
import cn.like.netty.common.serializer.MessageSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Create By like On 2021-04-11 14:41
 * 必须传入 {@link MessageCodecSharable}
 */
@EqualsAndHashCode(callSuper = true)
@ChannelHandler.Sharable
@Component
public class MessageCodecSharable extends MessageToMessageCodec<ByteBuf, Message> {

    /** 日志 */
    private final static Logger log = getLogger(MessageCodecSharable.class);
    /** 魔数 */
    @Value("${magicNumber}")
    public static String magicNumber;
    /** json 映射器 */
    private final ObjectMapper mapper = new ObjectMapper();

    private int version = 1;

    @Autowired
    private MessageSerializer messageSerializer;

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> outList) throws Exception {
        final ByteBuf out = ctx.alloc().buffer();
        out.writeBytes(magicNumber.getBytes());
        out.writeInt(version);  // TODO: 2021/4/13 暂时全为1
        out.writeByte(messageSerializer.algorithmType());
        out.writeByte(msg.getMessageType());
        out.writeInt(msg.getSequenceId());

        byte[] msgArray = messageSerializer.serialization(msg); // 序列化

        out.writeInt(msgArray.length); // 不可变 共22 个字节
        out.writeBytes(msgArray);

        outList.add(out);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        String magicNumber = in.readBytes(8).toString(Charset.defaultCharset());
        if (!"LikeLove".equals(magicNumber)) return;

        int version = in.readInt();
        byte serializationType = in.readByte();
        byte messageType = in.readByte();
        int seqId = in.readInt();
        int msgArrayLen = in.readInt();

        byte[] msg = new byte[msgArrayLen];
        in.readBytes(msg, 0, msgArrayLen);

        out.add(messageSerializer.deserialization(Message.getMessageClass(messageType), msg));  // 反序列化
    }
}
