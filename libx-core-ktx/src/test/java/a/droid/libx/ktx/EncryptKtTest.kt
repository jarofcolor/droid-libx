package a.droid.libx.ktx

import org.junit.Test

import org.junit.Assert.*

class EncryptKtTest {
    @Test
    fun md5() {
        println(123.md5())
        println("123".md5())
        println(123.0.md5())
        println("123.0".md5())
        println(byteArrayOf(123).md5())
    }
}