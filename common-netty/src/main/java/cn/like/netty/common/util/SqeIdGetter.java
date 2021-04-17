package cn.like.netty.common.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: Message  sequenceId 生成器
 */
public abstract class SqeIdGetter {
    public static final AtomicInteger id = new AtomicInteger();

    public static final int getId() {
        return id.incrementAndGet();
    }
}