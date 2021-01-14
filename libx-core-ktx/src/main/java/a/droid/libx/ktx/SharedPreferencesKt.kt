package a.droid.libx.ktx

import a.droid.libx.core.SharedPreferencesX
import android.content.Context
import android.content.SharedPreferences

fun SharedPreferences.apply(
        session: SharedPreferences.Editor.() -> Unit) {
    SharedPreferencesX.on(this).commit {
        session(it)
    }.apply()
}

fun SharedPreferences.clear() {
    SharedPreferencesX.on(this).clear().apply();
}

fun <T> SharedPreferences.get(key: String): T {
    return SharedPreferencesX.on(this).get<T>(key)
}

fun Context.shared(name: String, mode: Int = Context.MODE_PRIVATE): SharedPreferences {
    return this.getSharedPreferences(name, mode)
}