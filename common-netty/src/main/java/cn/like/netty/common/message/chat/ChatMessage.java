package cn.like.netty.common.message.chat;

import cn.like.netty.common.message.Message;
import cn.like.netty.common.util.SeqIdGetter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Create By like On 2021-04-18 13:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ChatMessage extends Message {

    private String from;
    private String to;
    private String message;

    public ChatMessage(String from, String to, String message) {
        setSequenceId(SeqIdGetter.getId());
        this.from = from;
        this.to = to;
        this.message = message;
    }

    @Override
    public int getMessageType() {
        return MessageTypeConstant.ChatMessage;
    }
}
