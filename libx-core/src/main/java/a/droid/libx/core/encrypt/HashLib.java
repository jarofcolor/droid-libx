package a.droid.libx.core.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashLib {
    private String algorithm;
    private static final char[] hexDigits = { // 用来将字节转换成 16 进制表示的字符
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f'};

    public HashLib(String algorithm) {
        this.algorithm = algorithm;
    }

    public String encode(File file) {
        try {
            MessageDigest MD5 = MessageDigest.getInstance(algorithm);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                MD5.update(buffer, 0, length);
            }
            return new String(covert(MD5.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public String encode(String str) {
        try {
            MessageDigest MD5 = MessageDigest.getInstance(algorithm);
            MD5.update(str.getBytes());
            return new String(covert(MD5.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String encode(byte[] data) {
        try {
            MessageDigest MD5 = MessageDigest.getInstance(algorithm);
            MD5.update(data);
            return new String(covert(MD5.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private char[] covert(byte[] mdBytes) {
        char[] str = new char[mdBytes.length * 2];
        int k = 0;
        for (byte temp : mdBytes) {
            str[k++] = hexDigits[temp >>> 4 & 0xf];
            str[k++] = hexDigits[temp & 0xf];
        }

        return str;
    }
}
