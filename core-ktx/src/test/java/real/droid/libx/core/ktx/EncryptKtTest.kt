package real.droid.libx.core.ktx

import org.junit.Test

import org.junit.Assert.*

class EncryptKtTest {
    @Test
    fun md5() {
        assertEquals(123.md5(),"202cb962ac59075b964b07152d234b70")
        assertEquals("123".md5(),"202cb962ac59075b964b07152d234b70")
        assertEquals("123".toByteArray().md5(),"202cb962ac59075b964b07152d234b70")

        assertEquals(123.0.md5(),"d4904ace98f824e7bf3485376742133f")
        assertEquals("123.0".md5(),"d4904ace98f824e7bf3485376742133f")
    }
}