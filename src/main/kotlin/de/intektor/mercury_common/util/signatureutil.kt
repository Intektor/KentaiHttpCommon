package de.intektor.mercury_common.util

import java.security.PrivateKey
import java.security.PublicKey
import java.security.Signature
import java.util.*

/**
 * @author Intektor
 */

private val signer = Signature.getInstance("SHA1WithRSA")

fun sign(text: String, key: PrivateKey) = sign(text.toByteArray(), key)

fun sign(toSign: ByteArray, key: PrivateKey): ByteArray {
    signer.initSign(key)
    signer.update(toSign)
    return signer.sign()
}

fun verify(text: String, signature: ByteArray, key: PublicKey) = verify(text.toByteArray(), signature, key)

fun verify(toVerify: ByteArray, signature: ByteArray, key: PublicKey): Boolean {
    signer.initVerify(key)
    signer.update(toVerify)
    return signer.verify(signature)
}

fun signUserUUID(userUUID: UUID, privateKey: PrivateKey): ByteArray = sign(userUUID.toString(), privateKey)

fun checkUserUUIDSign(userUUID: UUID, signature: ByteArray, publicKey: PublicKey): Boolean = verify(userUUID.toString(), signature, publicKey)