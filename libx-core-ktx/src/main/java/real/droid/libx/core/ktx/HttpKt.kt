package real.droid.libx.core.ktx

import real.droid.libx.core.http.HttpX

fun String.httpGet(): HttpX? {
    return HttpX.get(this)
}

fun String.httpPost(): HttpX? {
    return HttpX.post(this)
}