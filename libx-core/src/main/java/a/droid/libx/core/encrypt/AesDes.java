package a.droid.libx.core.encrypt;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AesDes {

    static final String KEY_AES = "AES";
    static final String KEY_DES = "AES";

    private final String algorithm;

    public AesDes(String algorithm) {
        this.algorithm = algorithm;
    }

    public byte[] encode(String data, String key) {
        return encode(data, key.getBytes());
    }

    public byte[] encode(String data, byte[] key) {
        return encode(data.getBytes(), key);
    }

    public byte[] encode(byte[] data, String key) {
        return encode(data, key.getBytes());
    }

    public byte[] encode(byte[] data, byte[] key) {
        return handle(data, key, false);
    }


    public byte[] decode(String data, String key) {
        return decode(data, key.getBytes());
    }

    public byte[] decode(String data, byte[] key) {
        return decode(data.getBytes(), key);
    }

    public byte[] decode(byte[] data, String key) {
        return decode(data, key.getBytes());
    }

    public byte[] decode(byte[] data, byte[] key) {
        return handle(data, key, true);
    }

    private byte[] handle(byte[] data, byte[] key, boolean isDecrypt) {
        try {
            SecureRandom sr = new SecureRandom();
            SecretKey secretKey = new SecretKeySpec(key, algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(isDecrypt ? Cipher.DECRYPT_MODE : Cipher.ENCRYPT_MODE, secretKey, sr);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
