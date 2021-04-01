package com.bestwu.jdbc.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.Set;

/**
 * @author Best Wu
 * @date 2021/3/23 15:08 <br>
 */
public class PropertiesUtil {

    public static <T> T getProps(Properties properties, T t, String keyPrefix) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (null == properties) {
            throw new IllegalArgumentException("Properties must not be null");
        } else if (null == t) {
            throw new IllegalArgumentException("instance must not be null");
        }

        Set<Object> keySet = properties.keySet();
        for (Object obj : keySet) {
            String key = String.valueOf(obj);
            if (key.startsWith(keyPrefix)) {
                // 获取指定实例的所有字段
                Field[] fields = t.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (key.endsWith(field.getName())) {
                        field.setAccessible(true);
                        field.set(t, field.getType().getConstructor(String.class).newInstance(properties.getProperty(key)));
                    }
                }
            }
        }
        return t;
    }
}
