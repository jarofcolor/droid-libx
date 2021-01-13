package a.droid.libx.ktx

import a.droid.libx.core.http.HttpX

fun String.httpGet(): HttpX? {
    return HttpX.get(this)
}

fun String.httpPost(): HttpX? {
    return HttpX.post(this)
}