package com.gyh.ssm.utils;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MD5Utils {

    /**
     * 普通MD5加密 01
     * <p>
     *
     * @Title : getStrMD5
     *        </p>
     *        <p>
     * @Description : TODO
     *              </p>
     *              <p>
     * @Author : HuaZai
     *         </p>
     *         <p>
     * @Date : 2017年12月26日 下午2:49:44
     *       </p>
     */
    public static String getStrMD5(String inStr) {
        // 获取MD5实例
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return "";
        }

        // 将加密字符串转换为字符数组
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        // 开始加密
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] digest = md5.digest(byteArray);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            int var = digest[i] & 0xff;
            if (var < 16)
                sb.append("0");
            sb.append(Integer.toHexString(var));
        }
        return sb.toString();
    }

    /**
     * 普通MD5加密 02
     * <p>
     *
     * @Title : getStrrMD5
     *        </p>
     *        <p>
     * @Description : TODO
     *              </p>
     *              <p>
     * @Author : HuaZai
     *         </p>
     *         <p>
     * @Date : 2017年12月27日 上午11:18:39
     *       </p>
     */
    public static String getStrrMD5(String password) {

        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte strTemp[] = password.getBytes("UTF-8");
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte md[] = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * MD5双重解密
     * <p>
     *
     * @Title : getconvertMD5
     *        </p>
     *        <p>
     * @Description : TODO
     *              </p>
     *              <p>
     * @Author : HuaZai
     *         </p>
     *         <p>
     * @Date : 2017年12月26日 下午3:34:17
     *       </p>
     */
    public static String getconvertMD5(String inStr) {
        char[] charArray = inStr.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = (char) (charArray[i] ^ 't');
        }
        String str = String.valueOf(charArray);
        return str;
    }

    /**
     * 使用Apache的Hex类实现Hex(16进制字符串和)和字节数组的互转
     * <p>
     *
     * @Title : md5Hex
     *        </p>
     *        <p>
     * @Description : TODO
     *              </p>
     *              <p>
     * @Author : HuaZai
     *         </p>
     *         <p>
     * @Date : 2017年12月27日 上午11:28:25
     *       </p>
     */
    @SuppressWarnings("unused")
    private static String md5Hex(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(str.getBytes());
            return new String(new Hex().encode(digest));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return "";
        }
    }

    /**
     * 加盐MD5加密
     * <p>
     *
     * @Title : getSaltMD5
     *        </p>
     *        <p>
     * @Description : TODO
     *              </p>
     *              <p>
     * @Author : HuaZai
     *         </p>
     *         <p>
     * @Date : 2017年12月27日 上午11:21:00
     *       </p>
     */
    public static String getSaltMD5(String password, String salt) {

        // 生成最终的加密盐
        String Salt = salt;
        password = md5Hex(password + Salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = Salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return String.valueOf(cs);
    }

    public static String getSalt() {
        // 生成一个16位的随机数
        Random random = new Random();
        StringBuilder sBuilder = new StringBuilder(16);
        sBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = sBuilder.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sBuilder.append("0");
            }
        }
        return sBuilder.toString();
    }

    /**
     * 验证加盐后是否和原文一致
     * <p>
     *
     * @Title : verifyMD5
     *        </p>
     *        <p>
     * @Description : TODO
     *              </p>
     *              <p>
     * @Author : HuaZai
     *         </p>
     *         <p>
     * @Date : 2017年12月27日 下午2:22:22
     *       </p>
     */
    public static boolean getSaltverifyMD5(String origin, String md5) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5.charAt(i);
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
            cs2[i / 3] = md5.charAt(i + 1);
        }
        String Salt = new String(cs2);
        return md5Hex(origin + Salt).equals(String.valueOf(cs1));
    }

}
