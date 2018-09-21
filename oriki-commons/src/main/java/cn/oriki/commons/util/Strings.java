package cn.oriki.commons.util;

import java.util.Objects;

/**
 * 字符串操作工具
 *
 * @author oriki.wang
 */
public class Strings {

    public static final String EMPTY_STRING = "";

    /**
     * 符合条件：字符串为 null 或 "" 或为 " "... ，返回true
     *
     * @param string 待检验字符串
     * @return 待检验字符串为 null 或 "" 返回true
     */
    public static boolean isBlank(CharSequence string) {
        if (Objects.isNull(string)) {
            return true;
        }

        int strLength;
        if ((strLength = string.length()) != 0) {
            for (int i = 0; i < strLength; ++i) {
                if (!Character.isWhitespace(string.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 判断字符串不为 null 且不为 "" ，符合返回true
     *
     * @param string 待检验字符串
     * @return 待检验字符串不为 null 且不为 "" 返回true
     */
    public static boolean isNotBlank(CharSequence string) {
        return !isBlank(string);
    }

    /**
     * 判断 string 中是否包含 s
     *
     * @param string 待检测字符串
     * @param str    检测字符串
     * @return 包含 s 返回 true ，不包含或 s 为空字符串返回 false
     * @throws IllegalArgumentException string 为空时抛出的异常
     */
    public static boolean isContain(String string, CharSequence str) {
        if (isBlank(string) && isNotBlank(str)) {
            return false;
        }
        return !isBlank(str) && string.contains(str);
    }

}
