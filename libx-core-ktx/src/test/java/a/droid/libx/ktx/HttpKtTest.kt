package a.droid.libx.ktx

import a.droid.libx.core.http.HttpX
import a.droid.libx.core.http.HttpX.IPlainTextResult
import org.junit.Test

class HttpKtTest {
    @Test
    fun get(){
        "https://www.baidu.com".httpGet()?.requestSync(object:IPlainTextResult{
            override fun onResult(data: String?) {
                println("data:$data")
            }

            override fun onError(code: Int, msg: String?) {
                println("error:code = $code,msg = $msg")
            }
        })
    }
}