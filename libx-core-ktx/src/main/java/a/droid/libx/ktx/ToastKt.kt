package a.droid.libx.ktx

import a.droid.libx.core.ToastX
import android.content.Context
import android.content.SharedPreferences

fun Context.toast(msg: String, isLong: Boolean = false) {
    ToastX.on(this, msg, isLong).show()
}

fun Context.toast(resId: Int, isLong: Boolean = false) {
    ToastX.on(this, resId, isLong).show()
}

fun String.toast(context: Context, isLong: Boolean = false) {
    context.toast(this, isLong)
}

fun Int.toast(context: Context, isLong: Boolean = false) {
    try {
        context.resources.getString(this)
        context.toast(this, isLong)
    } catch (e: Exception) {
        context.toast(this.toString(), isLong)
    }
}

fun Float.toast(context: Context, isLong: Boolean = false) {
    context.toast(this.toString(), isLong)
}

fun Double.toast(context: Context, isLong: Boolean = false) {
    context.toast(this.toString(), isLong)
}

fun Char.toast(context: Context, isLong: Boolean = false) {
    context.toast(this.toString(), isLong)
}

fun Boolean.toast(context: Context, isLong: Boolean = false) {
    context.toast(this.toString(), isLong)
}
