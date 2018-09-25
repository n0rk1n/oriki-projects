package cn.oriki.commons.util;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * 集合相关操作的工具
 *
 * @author oriki.wang
 */
public class Collections {

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
     * 集合不为空并且存在数据返回 true
     *
     * @param collection 集合
     * @return 集合不为空并且存在数据的情况返回 true
     */
    public static boolean nonNullAndHasElements(Collection<?> collection) {
        return !isNullOrEmpty(collection);
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

    /**
     * 集合中的元素使用 separator 拼接
     * <p>
     * 拼接会过滤其中为空的元素
     *
     * @param collection Collections对象
     * @param separator  分隔符
     * @return 使用 separator 拼接的字符串
     */
    public static String join(Collection<?> collection, String separator) {
        if (isNullOrEmpty(collection)) {
            // 不存在映射或为没有元素，返回空字符串
            return Strings.EMPTY_STRING;
        } else {
            Iterator<?> iterator = collection.iterator();
            if (!iterator.hasNext()) {
                return Strings.EMPTY_STRING;
            } else {
                Object first = iterator.next();
                if (!iterator.hasNext()) {
                    return Objects.isNull(first) ? Strings.EMPTY_STRING : first.toString();
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    if (Objects.nonNull(first)) {
                        stringBuilder.append(first);
                    }
                    while (iterator.hasNext()) {
                        Object obj = iterator.next();
                        if (Objects.isNull(obj)) {
                            continue;
                        }
                        if (Objects.nonNull(separator)) {
                            stringBuilder.append(separator);
                        }
                        stringBuilder.append(obj.toString());
                    }
                    return stringBuilder.toString();
                }
            }
        }
    }

    /**
     * Collection 根据 separator 进行拼接，生成字符串
     *
     * @param collection 集合
     * @param separator  分隔符
     * @param prefix     前缀字符串
     * @param suffix     后缀字符串
     * @return 拼接后的字符串
     */
    public static String join(Collection<?> collection, String separator, String prefix, String suffix) {
        return prefix + join(collection, separator) + suffix;
    }

    /**
     * 将 List 等分为 n 个 List
     *
     * @param list 待分割集合
     * @param n    等分数量
     * @param <T>  集合泛型
     * @return 等分后的集合
     */
    public static <T> List<List<T>> averageAssign(List<T> list, int n) {
        List<List<T>> result = new ArrayList<>();
        // 计算余数
        int remainder = list.size() % n;
        // 计算商
        int quotient = list.size() / n;
        // 定义偏移量
        int offset = 0;
        for (int i = 0; i < n; i++) {
            List<T> value;
            if (remainder > 0) {
                value = list.subList(i * quotient + offset, (i + 1) * quotient + offset + 1);
                remainder--;
                offset++;
            } else {
                value = list.subList(i * quotient + offset, (i + 1) * quotient + offset);
            }
            if (Collections.nonNullAndHasElements(value)) {
                result.add(value);
            }
        }
        return result;
    }

    /**
     * 按照元素的个数对 List 进行等分
     *
     * @param list 集合
     * @param n    分割后每个集合的最大数量
     * @param <T>  范型
     * @return 等分后的集合
     */
    public static <T> List<List<T>> partition(List<T> list, int n) {
        return Lists.partition(list, n);
    }

    /**
     * 创建一个长度为 n 的集合，填充 o 对象
     * <p>
     * 实际调用的是 java.util.Collection 的 nCopy 方法
     *
     * @param n      集合长度
     * @param object 填充对象
     * @param <T>    泛型
     * @return 填充 o 对象的集合
     */
    public static <T> List<T> nCopies(int n, T object) {
        return java.util.Collections.nCopies(n, object);
    }

}
