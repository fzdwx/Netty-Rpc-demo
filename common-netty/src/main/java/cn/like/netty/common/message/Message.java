package cn.like.netty.common.message;


import cn.like.netty.common.message.auth.AuthRequest;
import cn.like.netty.common.message.auth.AuthResponse;
import cn.like.netty.common.message.chat.ChatMessage;
import cn.like.netty.common.message.heartbeat.HeartbeatRequest;
import cn.like.netty.common.message.heartbeat.HeartbeatResponse;
import cn.like.netty.common.message.rpc.RpcRequest;
import cn.like.netty.common.message.rpc.RpcResponse;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public abstract class Message implements Serializable {

    private static final Map<Integer, Class<?>> messageClasses = new HashMap<>();

    static {
        messageClasses.put(MessageTypeConstant.RpcResponse, RpcResponse.class);
        messageClasses.put(MessageTypeConstant.RpcRequest, RpcRequest.class);

        messageClasses.put(MessageTypeConstant.HeartbeatRequest, HeartbeatRequest.class);
        messageClasses.put(MessageTypeConstant.HeartbeatResponse, HeartbeatResponse.class);

        messageClasses.put(MessageTypeConstant.AuthRequest, AuthRequest.class);
        messageClasses.put(MessageTypeConstant.AuthResponse, AuthResponse.class);

        messageClasses.put(MessageTypeConstant.ServerMessage, ServerMessage.class);

        messageClasses.put(MessageTypeConstant.ChatMessage, ChatMessage.class);
    }

    private int sequenceId;
    private int messageType;

    public static Class<?> getMessageClass(int messageType) {
        return messageClasses.get(messageType);
    }

    public abstract int getMessageType();

    public static class MessageTypeConstant {

        public static final int RpcRequest = -4;
        public static final int RpcResponse = -3;

        public static final int HeartbeatRequest = -2;
        public static final int HeartbeatResponse = -1;

        public static final int ServerMessage = 0;

        public static final int AuthRequest = 1;
        public static final int AuthResponse = 2;

        public static final int ChatMessage = 3;

    }
}
