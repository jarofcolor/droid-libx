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

fun Number.sha(): String {
    return EncryptX.sha().encode(this.toString())
}

fun String.sha(): String {
    return EncryptX.sha().encode(this)
}

fun ByteArray.sha(): String {
    return EncryptX.sha().encode(String(this))
}

fun File.sha(): String {
    return EncryptX.sha().encode(this)
}

fun Number.sha256(): String {
    return EncryptX.sha256().encode(this.toString())
}

fun String.sha256(): String {
    return EncryptX.sha256().encode(this)
}

fun ByteArray.sha256(): String {
    return EncryptX.sha256().encode(String(this))
}

fun File.sha256(): String {
    return EncryptX.sha256().encode(this)
}

fun Number.sha512(): String {
    return EncryptX.sha512().encode(this.toString())
}

fun String.sha512(): String {
    return EncryptX.sha512().encode(this)
}

fun ByteArray.sha512(): String {
    return EncryptX.sha512().encode(String(this))
}

fun File.sha512(): String {
    return EncryptX.sha512().encode(this)
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

fun File.base64(dst: File, isDecoded: Boolean = false, isNewline: Boolean = false): Boolean {
    return if (!isDecoded)
        EncryptX.base64().encode(this, dst, isNewline)
    else
        EncryptX.base64().decode(this, dst, isNewline)
}

fun File.base64(dst: File, isDecoded: Boolean = false, lineLength: Int = 76, lineSeparator: String = ""): Boolean {
    return if (!isDecoded)
        EncryptX.base64().encode(this, dst, lineLength, lineSeparator)
    else
        EncryptX.base64().decode(this, dst, lineSeparator.isNotEmpty())
}
