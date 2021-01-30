package real.droid.libx.core.ktx

import real.droid.libx.core.RuntimeX
import real.droid.libx.core.ThreadX

fun system(cmd: String): RuntimeX.Result? {
    return RuntimeX.on().runtimeCommand(cmd)
}

fun systemAsync(cmd: String, callback: (result: RuntimeX.Result) -> Unit) {
    ThreadX.create().post {
        var result = RuntimeX.on().runtimeCommand(cmd)
        ThreadX.ui().post {
            callback(result)
        }
    }
}
