package cn.like.netty.common.serializer;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * Create By like On 2021-04-13 20:33
 * <p>
 * JSON 序列化
 */
@Component
public class JSONMessageSerializerImpl implements MessageSerializer {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> byte[] serialization(T object) {
        try {
            return mapper.writeValueAsString(object).getBytes();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("序列化失败:{}", e);
        }
    }

    @Override
    public <T> T deserialization(Class<T> clazz, byte[] bytes) {
        String json = StrUtil.str(bytes, Charset.defaultCharset());
        return (T) JSONUtil.toBean(json, clazz);
    }

    @Override
    public int algorithmType() {
        return MessageSerializerAlgorithmType.JSON.code;
    }
}
