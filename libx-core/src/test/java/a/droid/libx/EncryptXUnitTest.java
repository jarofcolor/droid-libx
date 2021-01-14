package a.droid.libx;

import org.junit.Test;

import java.io.File;

import a.droid.libx.core.encrypt.EncryptX;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EncryptXUnitTest {
    @Test
    public void base64() {
        String str = "Ahjdfhuddjkcdufguefhevedfvgerufeocfndkvurfgurevbrf=Xxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

        //has not line separator
        String encodedStr = new String(EncryptX.base64().encode(str));
        String decodedStr = new String((EncryptX.base64().decode(encodedStr)));
        assertEquals(encodedStr, "QWhqZGZodWRkamtjZHVmZ3VlZmhldmVkZnZnZXJ1ZmVvY2ZuZGt2dXJmZ3VyZXZicmY9WHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHg=");
        assertEquals(decodedStr, str);

        //has line separator
        encodedStr = new String(EncryptX.base64().encode(str, true));
        decodedStr = new String((EncryptX.base64().decode(encodedStr, true)));

        assertEquals(encodedStr, "QWhqZGZodWRkamtjZHVmZ3VlZmhldmVkZnZnZXJ1ZmVvY2ZuZGt2dXJmZ3VyZXZicmY9WHh4eHh4\r\neHh4eHh4eHh4eHh4eHh4eHh4eHh4eHg=");
        assertEquals(decodedStr, str);

        //custom line separator
        encodedStr = new String(EncryptX.base64().encode(str, 10, "\t"));
        decodedStr = new String(EncryptX.base64().decode(encodedStr, true));
        assertEquals(encodedStr, "QWhqZGZo\tdWRkamtj\tZHVmZ3Vl\tZmhldmVk\tZnZnZXJ1\tZmVvY2Zu\tZGt2dXJm\tZ3VyZXZi\tcmY9WHh4\teHh4eHh4\teHh4eHh4\teHh4eHh4\teHh4eHh4\teHg=");
        assertEquals(decodedStr, str);

        //file encoded
        String srcFilePath = getClass().getResource("/test.txt").getFile();
        System.out.println(srcFilePath);
        File src = new File(srcFilePath);
        File dst = new File(srcFilePath + ".base64");
        boolean isSuccess = EncryptX.base64().encode(src, dst);
        assertTrue(isSuccess);

        //file decoded
        src = new File(srcFilePath + ".base64");
        dst = new File(srcFilePath + ".base64.decoded");
        isSuccess = EncryptX.base64().decode(src, dst);
        assertTrue(isSuccess);
    }

    @Test
    public void md5() {
        String str = "Ahjdfhuddjkcdufguefhevedfvgerufeocfndkvurfgurevbrf=X";
        String encodedStr = EncryptX.md5().encode(str);
        System.out.println(encodedStr);
        assertEquals(encodedStr, "b1aa16eb6e68fc3319c697ba3e7f66c2");
    }

    @Test
    public void sha() {
        String str = "Ahjdfhuddjkcdufguefhevedfvgerufeocfndkvurfgurevbrf=X";
        String encodedStr = EncryptX.sha().encode(str);
        System.out.println(encodedStr);
        assertEquals(encodedStr, "0c37c6a6ead1264c42d8a3800304ca7c8565c337");
    }

    @Test
    public void aes() {
        String str = "Ahjdfhuddjkcdufguefhevedfvgerufeocfndkvurfgurevbrf=X";
        String key = "qwer============";

        byte[] encodedBytes = EncryptX.aes().encode(str, key);
        assertEquals(new String(EncryptX.aes().decode(encodedBytes, key)), str);
    }

    @Test
    public void des() {
        String str = "Ahjdfhuddjkcdufguefhevedfvgerufeocfndkvurfgurevbrf=X";
        String key = "qwer============";

        byte[] encodedBytes = EncryptX.des().encode(str, key);
        assertEquals(new String(EncryptX.des().decode(encodedBytes, key)), str);
    }

}
