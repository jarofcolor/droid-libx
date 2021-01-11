package a.droid.libx.core.encrypt;

public class EncryptX {
    public static HashLib md5() {
        return new HashLib("MD5");
    }

    public static HashLib sha() {
        return new HashLib("SHA1");
    }

    public static HashLib sha256() {
        return new HashLib("SHA-256");
    }

    public static HashLib sha512() {
        return new HashLib("SHA-512");
    }

    public static Base64 base64() {
        return new Base64();
    }

}
