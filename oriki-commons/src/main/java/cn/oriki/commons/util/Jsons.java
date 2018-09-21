package cn.oriki.commons.util;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Objects;

/**
 * Json 工具类
 *
 * @author oriki
 */
public class Jsons {

    private static final String EMPTY_OBJECT_JSON = "{}";
    private static final String EMPTY_COLLECTION_JSON = "[]";

    /**
     * json 转 object 的方法
     *
     * @param text  Json
     * @param clazz 待转换对象字节码
     * @param <T>   泛型
     * @return entity 实体
     */
    public static <T> T toObject(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    /**
     * object 转 json 的方法
     *
     * @param object entity 对象
     * @return 转换后的 Json
     */
    public static <S> String toJson(S object) {
        if (Objects.isNull(object)) {
            return EMPTY_OBJECT_JSON;
        } else if (Collections.isCollection(object) && Collections.isNullOrEmpty((Collection<?>) object)) {
            // 为空集合
            return EMPTY_COLLECTION_JSON;
        } else if (object.getClass().isArray() && 0 == Array.getLength(object)) {
            // 为空数组
            return EMPTY_COLLECTION_JSON;
        }
        return JSON.toJSONString(object);
    }

}