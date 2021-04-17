package cn.like.netty.common.message.rpc;

import cn.like.netty.common.message.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Create By like On 2021-04-14 15:24
 * <p>
 * rpc 请求消息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RpcRequestMessage extends Message {

    /** 调用的接口名字 */
    private String interfaceName;
    /** 调用的方法名称 */
    private String methodName;
    /** 方法返回类型 */
    private String returnType;
    /** 方法参数类型 */
    private String[] parameterTypes;
    /** 方法参数值 */
    private Object[] parameterValue;

    public RpcRequestMessage(int sequenceId, String interfaceName, String methodName, String returnType, String[] parameterTypes, Object[] parameterValue) {
        this.setSequenceId(sequenceId);
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        this.parameterValue = parameterValue;
    }

    @Override
    public int getMessageType() {
        return RpcRequestMessage;
    }
}
