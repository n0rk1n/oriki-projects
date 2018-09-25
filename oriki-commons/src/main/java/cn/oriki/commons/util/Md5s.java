package cn.oriki.commons.util;

import lombok.NonNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Md5 加密
 *
 * @author oriki.wang
 */
public class Md5s {

    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private Md5s() {
    }

    public static String getMd5(@NonNull String string) throws NoSuchAlgorithmException {
        return hexDigest(string.getBytes());
    }

    /**
     * 加密的方法
     *
     * @param bytes byte 数组
     * @return 密文
     * @throws NoSuchAlgorithmException 无法创建加密抛出的对象
     */
    private static String hexDigest(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest e = MessageDigest.getInstance("MD5");
        e.update(bytes);
        byte[] tmp = e.digest();
        char[] str = new char[32];
        int k = 0;

        for (int i = 0; i < 16; ++i) {
            byte byte0 = tmp[i];
            str[k++] = HEX_DIGITS[byte0 >>> 4 & 15];
            str[k++] = HEX_DIGITS[byte0 & 15];
        }

        return new String(str);
    }

}