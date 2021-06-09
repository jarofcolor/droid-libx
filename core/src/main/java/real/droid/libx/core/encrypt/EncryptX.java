package real.droid.libx.core.encrypt;

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

    public static HashLib hashLib(String algorithm) {
        return new HashLib(algorithm);
    }

    public static Base64 base64() {
        return new Base64();
    }

    public static AesDes aes() {
        return new AesDes(AesDes.KEY_AES);
    }

    public static AesDes des() {
        return new AesDes(AesDes.KEY_DES);
    }

}
