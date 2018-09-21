package cn.oriki.commons.util;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数工具
 *
 * @author oriki
 */
public class Randoms {

    private static final String NUMBER_0_9 = "0123456789";
    private static final String UPPERCASE_WORD_A_Z = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE_WORD_A_Z = "abcdefghijklmnopqrstuvwxyz";

    private static final String WORD_A_Z = UPPERCASE_WORD_A_Z + LOWER_CASE_WORD_A_Z;
    private static final String NUMBER_WORD_CODE = WORD_A_Z + NUMBER_0_9;

    /**
     * 获取固定长度 random number
     *
     * @param length 随机数的长度
     * @return random number
     */
    public static String getRandomNumber(int length) {
        return getRandom(NUMBER_0_9, length);
    }

    /**
     * 获取 length 位 随机数字 & 字母验证码
     * <p>
     * 字母包含大小写
     *
     * @return 4 位格式验证码
     */
    public static String getCode(int length) {
        return getRandom(NUMBER_WORD_CODE, length);
    }

    /**
     * 获取32位长度的 uuid
     *
     * @return uuid
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取一个从 0 到 max 的整数数字
     * <p>
     * 生成一个随机的 int 值，该值介于 [0,n) 的区间，也就是 0 到 n 之间的随机 int 值，包含 0 而不包含 n
     *
     * @param max 最大值
     * @return 0 ~ max 的随机数
     */
    public static int randomInt(int max) {
        return getRandom().nextInt(max);
    }

    /**
     * 给定字符串随机生成 random code
     *
     * @param targetString 目标随机字符串
     * @param length       获取长度
     * @return 随机字符串
     */
    private static String getRandom(String targetString, int length) {
        // length must >0
        if (length <= 0 || Strings.isBlank(targetString)) {
            return Strings.EMPTY_STRING;
        } else {
            StringBuilder stringBuilder = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                int index = randomInt(targetString.length());
                char c = targetString.charAt(index);
                stringBuilder.append(c);
            }
            return stringBuilder.toString();
        }
    }

    private static Random getRandom() {
        // return new Random();
        // 换用线程更加安全的 ThreadLocalRandom
        return ThreadLocalRandom.current();
    }

}
