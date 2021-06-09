package real.droid.libx.core.ktx

import real.droid.libx.core.http.HttpX.IPlainTextResult
import org.junit.Test

class HttpKtTest {
    @Test
    fun get() {
        "https://www.baidu.com".httpGet()?.request(object : IPlainTextResult {
            override fun onResult(data: String?) {
                println("data:$data")
            }

            override fun onError(code: Int, msg: String?) {
                println("error:code = $code,msg = $msg")
            }
        })
    }
}