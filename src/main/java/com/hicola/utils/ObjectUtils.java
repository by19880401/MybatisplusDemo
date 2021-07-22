package com.hicola.utils;

import cn.hutool.log.StaticLog;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * 在effective java（这本书真的很棒）中说道，最佳的单例实现模式就是枚举模式。利用枚举的特性，让JVM来帮我们保证线程安全和单一实例的问题。除此之外，写法还特别简单
 *
 * @author bai.yang email:willis.bai@outlook.com
 * @date 2021/7/22
 * @
 */
public enum ObjectUtils {
    INSTANCE;

    /**
     * 判断对象中的所有属性是否都为null
     *
     * @param object
     * @return
     */
    public boolean isObjAllFieldsNull(Object object) {
        if (null == object) {
            return true;
        }
        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(object) != null && StringUtils.isNotBlank(f.get(object).toString())) {
                    return false;
                }
            }
        } catch (Exception e) {
            StaticLog.warn("有异常：{}", e);
        }
        return true;
    }
}
