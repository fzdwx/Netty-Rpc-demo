package cn.like.netty.common.serializer;

/**
 * 序列化算法类型
 *
 * @Description: 默认提供 JSON,JDK实现
 * @see MessageSerializer
 */
public enum MessageSerializerAlgorithmType {

    JSON(1, JSONMessageSerializerImpl.class),
    JDK(0, JDKMessageSerializerImpl.class);

    public Class<?> clazz;
    public int code;

    MessageSerializerAlgorithmType(int code, Class<?> clazz) {
        this.clazz = clazz;
        this.code = code;
    }
}