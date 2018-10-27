package de.intektor.mercury_common

import de.intektor.mercury_common.util.sign
import de.intektor.mercury_common.util.verify
import org.junit.Test
import java.security.KeyPairGenerator
import java.util.*

/**
 * @author Intektor
 */
class SignatureTest {

    @Test
    fun checkVerify() {
        val gen = KeyPairGenerator.getInstance("RSA")
        gen.initialize(512)
        val pair = gen.genKeyPair()
        val private = pair.private
        val public = pair.public

        val toVerify = ByteArray(64)
        Random(4294).nextBytes(toVerify)

        val signature = sign(toVerify, private)

        assert(verify(toVerify, signature, public))
    }
}