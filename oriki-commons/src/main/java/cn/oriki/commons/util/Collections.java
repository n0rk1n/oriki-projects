package cn.oriki.commons.util;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Objects;

/**
 * 集合相关操作的工具
 *
 * @author oriki.wang
 */
public class Collections {

    /**
     * 集合不为空并且存在数据返回 true
     *
     * @param collection 集合
     * @return 集合不为空并且存在数据的情况返回 true
     */
    public static boolean nonNullAndHasElements(Collection<?> collection) {
        return !isNullOrEmpty(collection);
    }

    /**
     * 集合为空 或者 集合没有数据返回 true
     *
     * @param collection 集合
     * @return 集合为空或者不存在数据返回 true
     */
    public static boolean isNullOrEmpty(Collection<?> collection) {
        return Objects.isNull(collection) || collection.isEmpty();
    }

    /**
     * 判断对象是否为集合或者为枚举集
     *
     * @param e 待判断对象
     * @return 如果是集合子类，返回 true
     */
    public static <E> boolean isCollection(E e) {
        return e instanceof Iterable || e instanceof Enumeration;
    }

}
