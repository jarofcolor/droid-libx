package a.droid.libx.core.http;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HttpX {
    public interface IResult {
        void onError(int code, String msg);
    }

    public interface IPlainTextResult extends IResult {
        void onResult(String data);
    }

    public interface IBytesResult extends IResult {
        void onResult(byte[] data, int offset, int len);
    }

    enum Method {
        GET("GET"), POST("POST");
        private final String name;

        Method(String method) {
            this.name = method;
        }

        public String getName() {
            return name;
        }
    }

    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final int DEFAULT_TIMEOUT = 5000;
    public static final int CODE_REQUEST_EXCEPTION = Integer.MAX_VALUE;
    private static Executor executor = Executors.newCachedThreadPool();
    private final Method method;
    private HashMap<String, String> headers;
    private HashMap<String, String> params;
    private int readTimeout = DEFAULT_TIMEOUT;
    private int connectTimeout = DEFAULT_TIMEOUT;
    private String url;

    private HttpX(String url, Method method) {
        this.url = url;
        this.method = method;
        headers = new HashMap<>();
        params = new HashMap<>();
    }

    public static HttpX post(String url) {
        return new HttpX(url, Method.POST);
    }

    public static HttpX get(String url) {
        return new HttpX(url, Method.GET);
    }

    public HttpX readTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public HttpX connectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public HttpX headers(HashMap<String, String> headers) {
        if (headers != null && headers.size() > 0) {
            this.headers.putAll(headers);
        }
        return this;
    }


    public HttpX header(String key, String value) {
        this.headers.put(key, value);
        return this;
    }


    public HttpX params(HashMap<String, String> params) {
        if (params != null && params.size() > 0) {
            this.params.putAll(params);
        }
        return this;
    }


    public HttpX param(String key, String value) {
        this.params.put(key, value);
        return this;
    }

    public void requestSync(final IResult result) {
        exec(result);
    }

    public void request(final IResult result) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                exec(result);
            }
        });
    }


    private void exec(IResult result) {
        StringBuilder sbRequest = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sbRequest.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        String requestParamsStr = params.size() > 0 ? sbRequest.substring(0, sbRequest.length() - 1) : "";
        try {
            if (method == Method.GET) {
                url = url + "?" + requestParamsStr;
            }
            System.out.println("url:"+url);
            //由URL的openConnection方法得到一个HttpURLConnection（需要强转）
            HttpURLConnection httpURLConnection =
                    (HttpURLConnection) new URL(url).openConnection();
            //设置post提交
            httpURLConnection.setRequestMethod(method.getName());
            //设置超时时间
            httpURLConnection.setConnectTimeout(readTimeout);
            httpURLConnection.setReadTimeout(connectTimeout);

            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream os = null;
            if (method == Method.POST) {
                //把请求正文通过OutputStream发出去
                os = httpURLConnection.getOutputStream();
                os.write(requestParamsStr.getBytes());
                os.flush();
            }


            //判断响应码  200 代表成功
            int code = httpURLConnection.getResponseCode();
            if (code == 200) {
                //由HttpURLConnection拿到输入流
                InputStream in = httpURLConnection.getInputStream();
                StringBuffer sb = new StringBuffer();
                //根据输入流做一些IO操作
                byte[] buff = new byte[4 * 1024];
                int len;
                if (result instanceof IBytesResult) {
                    IBytesResult bytesResult = (IBytesResult) result;
                    while ((len = in.read(buff)) != -1) {
                        bytesResult.onResult(buff, 0, len);
                    }
                } else if (result instanceof IPlainTextResult) {
                    while ((len = in.read(buff)) != -1) {
                        sb.append(new String(buff, 0, len, UTF_8));
                    }
                    IPlainTextResult plainTextResult = (IPlainTextResult) result;
                    plainTextResult.onResult(sb.toString());
                }

                in.close();
                if (os != null)
                    os.close();
                httpURLConnection.disconnect();
            } else {
                result.onError(code, httpURLConnection.getResponseMessage());
            }
        } catch (Exception e) {
            result.onError(CODE_REQUEST_EXCEPTION, e.getMessage());
        }
    }
}
