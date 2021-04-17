package cn.like.netty.common.message.dispatcher;

import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Create By like On 2021-04-17 15:07
 * <p>
 * message 容器
 *
 * @see cn.like.netty.common.message.Message
 */
public class MessageContainer implements InitializingBean {

    private final static Logger log = getLogger(MessageContainer.class);
    /**
     * 消息类型与 MessageHandler 的映射
     */
    private final Map<Integer, MessageHandler> handlers = new HashMap<>();
    @Autowired
    private ApplicationContext applicationContext;


    @Override
    public void afterPropertiesSet() throws Exception {
        // 通过 ApplicationContext 获得所有 MessageHandler Bean
        applicationContext.getBeansOfType(MessageHandler.class).values() // 获得所有 MessageHandler Bean
                .forEach(messageHandler -> handlers.put(messageHandler.getMessageType(), messageHandler)); // 添加到 handlers 中

        log.info("[afterPropertiesSet][消息处理器数量：{}]", handlers.size());
    }

    /**
     * 获得类型对应的 MessageHandler
     *
     * @param messageType 类型
     * @return MessageHandler
     */
    MessageHandler getMessageHandler(Integer messageType) {
        MessageHandler handler = handlers.get(messageType);
        if (handler == null) {
            throw new IllegalArgumentException(String.format("类型(%s) 找不到匹配的 MessageHandler 处理器", messageType));
        }
        return handler;
    }
}
