package com.hicola.utils;

import cn.hutool.log.StaticLog;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * @author bai.yang email:willis.bai@outlook.com
 * @date 2021/7/22
 * @
 */
public enum ObjectUtils {
    INSTANCE;

    ObjectUtils() {
    }


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
