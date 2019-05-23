package my.koinoxrista.utils;

import java.security.MessageDigest;

public class MD5PasswordHasher {

    public static String hash(String str) {
        StringBuffer sb = new StringBuffer();

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());

            byte byteData[] = md.digest();

            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return sb.toString();
    }
}
