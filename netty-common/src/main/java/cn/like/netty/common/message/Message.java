package cn.like.netty.common.message;


import cn.like.netty.common.message.chat.*;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public abstract class Message implements Serializable {

    public static final int RpcRequestMessage = -7;
    public static final int RpcResponseMessage = -6;
    public static final int PongMessage = -5;
    public static final int PingMessage = -4;
    public static final int ServerMessage = -3;
    public static final int RegisterResponseMessage = -2;
    public static final int RegisterRequestMessage = -1;
    public static final int LoginRequestMessage = 0;
    public static final int LoginResponseMessage = 1;
    public static final int ChatRequestMessage = 2;
    public static final int ChatResponseMessage = 3;
    public static final int GroupCreateRequestMessage = 4;
    public static final int GroupCreateResponseMessage = 5;
    public static final int GroupJoinRequestMessage = 6;
    public static final int GroupJoinResponseMessage = 7;
    public static final int GroupQuitRequestMessage = 8;
    public static final int GroupQuitResponseMessage = 9;
    public static final int GroupChatRequestMessage = 10;
    public static final int GroupChatResponseMessage = 11;
    public static final int GroupMembersRequestMessage = 12;
    public static final int GroupMembersResponseMessage = 13;
    private static final Map<Integer, Class<?>> messageClasses = new HashMap<>();

    static {
        messageClasses.put(RpcResponseMessage, cn.like.netty.common.message.rpc.RpcResponseMessage.class);
        messageClasses.put(RpcRequestMessage, cn.like.netty.common.message.rpc.RpcRequestMessage.class);

        messageClasses.put(ServerMessage, ServerMessage.class);

        messageClasses.put(PingMessage, PingMessage.class);
        messageClasses.put(PongMessage, PongMessage.class);

        messageClasses.put(RegisterRequestMessage, cn.like.netty.common.message.chat.RegisterRequestMessage.class);
        messageClasses.put(RegisterResponseMessage, cn.like.netty.common.message.chat.RegisterResponseMessage.class);

        messageClasses.put(LoginRequestMessage, cn.like.netty.common.message.chat.LoginRequestMessage.class);
        messageClasses.put(LoginResponseMessage, cn.like.netty.common.message.chat.LoginResponseMessage.class);

        messageClasses.put(ChatRequestMessage, cn.like.netty.common.message.chat.ChatRequestMessage.class);
        messageClasses.put(ChatResponseMessage, ChatResponseMessage.class);

        messageClasses.put(GroupCreateRequestMessage, GroupCreateRequestMessage.class);
        messageClasses.put(GroupCreateResponseMessage, GroupCreateResponseMessage.class);

        messageClasses.put(GroupJoinRequestMessage, GroupJoinRequestMessage.class);
        messageClasses.put(GroupJoinResponseMessage, GroupJoinResponseMessage.class);

        messageClasses.put(GroupQuitRequestMessage, GroupQuitRequestMessage.class);
        messageClasses.put(GroupQuitResponseMessage, GroupQuitResponseMessage.class);

        messageClasses.put(GroupChatRequestMessage, GroupChatRequestMessage.class);
        messageClasses.put(GroupChatResponseMessage, GroupChatResponseMessage.class);

        messageClasses.put(GroupMembersRequestMessage, GroupMembersRequestMessage.class);
        messageClasses.put(GroupMembersResponseMessage, GroupMembersResponseMessage.class);
    }

    private int sequenceId;
    private int messageType;

    public static Class<?> getMessageClass(int messageType) {
        return messageClasses.get(messageType);
    }

    public abstract int getMessageType();

}
