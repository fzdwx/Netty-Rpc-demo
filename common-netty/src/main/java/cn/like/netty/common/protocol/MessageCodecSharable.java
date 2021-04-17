package cn.like.netty.common.protocol;

import cn.like.netty.common.message.Message;
import cn.like.netty.common.serializer.MessageSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.handler.codec.MessageToMessageCodec;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Create By like On 2021-04-11 14:41
 * 必须传入 {@link MessageCodecSharable}
 */
@ChannelHandler.Sharable
@Component
@ConfigurationProperties(prefix = "message-codec-sharable")
public class MessageCodecSharable extends MessageToMessageCodec<ByteBuf, Message> {

    /** 魔数 */
    public String magicNumber;

    /** 日志 */
    private final static Logger log = getLogger(MessageCodecSharable.class);

    public MessageCodecSharable() {
        System.out.println(magicNumber);
    }

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
        if (!this.magicNumber.equals(magicNumber)) return;

        int version = in.readInt();
        byte serializationType = in.readByte();
        byte messageType = in.readByte();
        int seqId = in.readInt();

        in.markReaderIndex(); // 标记当前读取位置
        if (in.readableBytes() <= 4) {  // 判断是否能够读取 length 长度
            return;
        }
        int msgLen = in.readInt();
        if (msgLen < 0) {
            throw new CorruptedFrameException("negative length: " + msgLen);
        }
        if (in.readableBytes() < msgLen) {  // 如果 message 不够可读，则退回到原读取位置;
            in.resetReaderIndex();
            return;
        }
        byte[] msg = new byte[msgLen];
        in.readBytes(msg, 0, msgLen);

        out.add(messageSerializer.deserialization(Message.getMessageClass(messageType), msg));  // 反序列化
    }

    public String getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(String magicNumber) {
        this.magicNumber = magicNumber;
    }
}
