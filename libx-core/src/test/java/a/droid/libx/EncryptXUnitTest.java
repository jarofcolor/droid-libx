package a.droid.libx;

import org.junit.Test;

import a.droid.libx.core.encrypt.EncryptX;

import static org.junit.Assert.*;

public class EncryptXUnitTest {
    @Test
    public void base64() {
        String str = "Ahjdfhuddjkcdufguefhevedfvgerufeocfndkvurfgurevbrf=X";

        //has not line separator
        String encodedStr = new String(EncryptX.base64().encode(str));
        String decodedStr = new String((EncryptX.base64().decode(encodedStr)));
        assertEquals(str, decodedStr);

        //has line separator
        encodedStr = new String(EncryptX.base64().encode(str, true));
        decodedStr = new String((EncryptX.base64().decode(encodedStr, true)));
        assertEquals(str, decodedStr);

        //custom line separator
        encodedStr = new String(EncryptX.base64().encode(str, 10, "\t"));
        decodedStr = new String(EncryptX.base64().decode(encodedStr, true));
        assertEquals(str, decodedStr);
    }

    @Test
    public void md5() {
        String str = "Ahjdfhuddjkcdufguefhevedfvgerufeocfndkvurfgurevbrf=X";
        String encodedStr = EncryptX.md5().encode(str);
        System.out.println(encodedStr);
        assertEquals(encodedStr.length(), 32);
    }
}
