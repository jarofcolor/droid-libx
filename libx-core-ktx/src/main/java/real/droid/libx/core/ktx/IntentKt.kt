package real.droid.libx.core.ktx

import real.droid.libx.core.IntentX
import android.annotation.TargetApi
import android.content.*
import android.os.Build
import android.os.Bundle
import android.os.Handler
import java.util.concurrent.Executor

fun Intent.startActivity(context: Context) {
    IntentX.on(context, this).startActivity()
}

fun Intent.startActivityForResult(context: Context, requestCode: Int) {
    IntentX.on(context, this).startActivityForResult(requestCode)
}

fun Intent.startService(context: Context): ComponentName? {
    return IntentX.on(context, this).startService()
}

@TargetApi(Build.VERSION_CODES.Q)
fun Intent.startForegroundService(context: Context): ComponentName? {
    return IntentX.on(context, this).startForegroundService()
}

fun Intent.bindService(context: Context, connection: ServiceConnection, flags: Int): Boolean {
    return IntentX.on(context, this).bindService(connection, flags)
}

fun Intent.bindService(context: Context, connection: ServiceConnection, flags: Int, executor: Executor): Boolean {
    return IntentX.on(context, this).bindService(connection, flags, executor)
}

fun Intent.sendBroadcast(context: Context) {
    IntentX.on(context, this).sendBroadcast()
}

fun Intent.sendBroadcast(context: Context, receiverPermission: String) {
    IntentX.on(context, this).sendBroadcast(receiverPermission)
}

fun Intent.sendBroadcast(context: Context, receiverPermission: Array<String>) {
    IntentX.on(context, this).sendBroadcast(receiverPermission)
}

fun Intent.sendOrderedBroadcast(context: Context, receiverPermission: String) {
    IntentX.on(context, this).sendOrderedBroadcast(receiverPermission)
}

fun Intent.sendOrderedBroadcast(context: Context, receiverPermission: String?, resultReceiver: BroadcastReceiver?,
                                scheduler: Handler?, initialCode: Int, initialData: String?,
                                initialExtras: Bundle?) {
    IntentX.on(context, this).sendOrderedBroadcast(receiverPermission, resultReceiver, scheduler, initialCode, initialData, initialExtras)
}