package cn.like.netty.common.util;

/**
 * Create By like On 2021-04-18 13:03
 * <p>
 * method 中的 parameterTypes 从String[] 和  Class<?>[]相互转换
 */
public class ProxyUtil {

    public static String[] classArrayToClassStringArray(Class<?>[] classes) {
        String[] parameterTypes = new String[classes.length];
        for (int i = 0; i < classes.length; i++) {
            parameterTypes[i] = classes[i].getName();
        }
        return parameterTypes;
    }

    public static Class<?>[] StringArrayToClassArray(String[] parameterTypes) {
        try {
            Class<?>[] classes = new Class[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                classes[i] = Class.forName(parameterTypes[i]);
            }
            return classes;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }
}
