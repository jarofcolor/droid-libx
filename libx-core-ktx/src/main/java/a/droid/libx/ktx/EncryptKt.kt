package a.droid.libx.ktx

import a.droid.libx.core.encrypt.EncryptX
import java.io.File

fun Number.md5(): String {
    return EncryptX.md5().encode(this.toString())
}

fun String.md5(): String {
    return EncryptX.md5().encode(this)
}

fun ByteArray.md5(): String {
    return EncryptX.md5().encode(String(this))
}

fun File.md5(): String {
    return EncryptX.md5().encode(this)
}

fun Number.base64(): ByteArray {
    return EncryptX.base64().encode(this.toString())
}

fun String.base64(): ByteArray {
    return EncryptX.base64().encode(this)
}

fun ByteArray.base64(): ByteArray {
    return EncryptX.base64().encode(this)
}