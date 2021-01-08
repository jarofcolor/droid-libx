package a.droid.libx.ktx

import a.droid.libx.core.SharedPreferencesX
import android.content.Context
import android.content.SharedPreferences

fun SharedPreferences.commit(isApply: Boolean = true, session: (editor: SharedPreferences.Editor) -> Unit) {
    SharedPreferencesX.on(this).commit(isApply) {
        session(it)
    }
}

fun <T> SharedPreferences.get(key: String): T {
    return SharedPreferencesX.on(this).get<T>(key)
}

fun Context.shared(name: String, mode: Int = Context.MODE_PRIVATE): SharedPreferences {
    return this.getSharedPreferences(name, mode)
}