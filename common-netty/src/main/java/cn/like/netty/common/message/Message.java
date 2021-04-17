package cn.like.netty.common.message;


import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public abstract class Message implements Serializable {

    public static final int RpcRequestMessage = -4;
    public static final int RpcResponseMessage = 3;
    public static final int PongMessage = 2;
    public static final int PingMessage = 1;
    public static final int ServerMessage = 0;

    private static final Map<Integer, Class<?>> messageClasses = new HashMap<>();

    static {
        messageClasses.put(RpcResponseMessage, cn.like.netty.common.message.rpc.RpcResponseMessage.class);
        messageClasses.put(RpcRequestMessage, cn.like.netty.common.message.rpc.RpcRequestMessage.class);

        messageClasses.put(PingMessage, PingMessage.class);
        messageClasses.put(PongMessage, PongMessage.class);

        messageClasses.put(ServerMessage, ServerMessage.class);
    }

    private int sequenceId;
    private int messageType;

    public static Class<?> getMessageClass(int messageType) {
        return messageClasses.get(messageType);
    }

    public abstract int getMessageType();

}
