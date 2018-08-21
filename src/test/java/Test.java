import com.gyh.ssm.utils.MD5Utils;
public class Test {

    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        MD5Utils md = new MD5Utils();
        String strMD5 = new String("abel");

        System.out.println("原始：" + strMD5);
        System.out.println("东东的：" + md.getStrrMD5(strMD5));
        System.out.println("MD5后：" + md.getStrMD5(strMD5));
        System.out.println("加密的：" + md.getconvertMD5(strMD5));
        System.out.println("解密的：" + md.getconvertMD5(md.getconvertMD5(strMD5)));

        System.out.println("\t\t=======================================");
        // 原文
        String plaintext = "abel";
        // plaintext = "123456";
        System.out.println("原始：" + plaintext);
        System.out.println("普通MD5后：" + MD5Utils.getStrMD5(plaintext));

        // 获取加盐后的MD5值
        String salt = MD5Utils.getSalt();
        System.out.println("盐：" + salt);
        String ciphertext = MD5Utils.getSaltMD5(plaintext, salt);
        System.out.println("加盐后MD5：" + ciphertext);
        System.out.println("是否是同一字符串:" + MD5Utils.getSaltverifyMD5(plaintext, ciphertext));
        /**
         * 其中某次DingSai字符串的MD5值
         */
        String[] tempSalt = { "959743a6272e941e6be51d3261d297d4b38e813305e3d686",
                "75c07db68845f8572cd1e034d57a8ed8868eb3bd0c32098c"};

        for (String temp : tempSalt) {
            System.out.println("是否是同一字符串:" + MD5Utils.getSaltverifyMD5(plaintext, temp));
        }
    }

}
