package a.droid.libx.ktx

import a.droid.libx.core.ToastX
import android.content.Context

fun Context.toast(msg: String, isLong: Boolean = false) {
    ToastX.toast(this, msg, isLong)
}