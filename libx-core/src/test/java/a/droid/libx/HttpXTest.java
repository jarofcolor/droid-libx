package a.droid.libx;

import org.junit.Test;

import a.droid.libx.core.http.HttpX;

public class HttpXTest {
    @Test
    public void testGet() {
        HttpX.get("https://www.baidu.com/").param("tn","simple").requestSync(new HttpX.IPlainTextResult() {
            @Override
            public void onResult(String data) {
                System.out.println("success:" + data);
            }

            @Override
            public void onError(int code, String msg) {
                System.out.println("error:" + "code = " + code + ",msg = " + msg);
            }
        });
    }

    @Test
    public void testPost(){
        HttpX.post("https://www.baidu.com/").param("tn","simple").requestSync(new HttpX.IPlainTextResult() {
            @Override
            public void onResult(String data) {
                System.out.println("success:" + data);
            }

            @Override
            public void onError(int code, String msg) {
                System.out.println("error:" + "code = " + code + ",msg = " + msg);
            }
        });
    }
}
