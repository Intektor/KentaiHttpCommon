package de.intektor.mercury_common.util

import com.google.common.hash.Hashing
import com.google.common.io.BaseEncoding
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.math.BigInteger
import java.security.*
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.RSAPrivateKeySpec
import java.security.spec.RSAPublicKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


/**
 * @author Intektor
 */
fun String.encryptRSA(key: Key): String {
    val encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
    encryptCipher.init(Cipher.ENCRYPT_MODE, key)
    val cipherText = encryptCipher.doFinal(this.toByteArray(charset("UTF-8")))
    return BaseEncoding.base64().encode(cipherText)
}

fun String.decryptRSA(key: Key): String {
    val bytes = BaseEncoding.base64().decode(this)
    val decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
    decryptCipher.init(Cipher.DECRYPT_MODE, key)
    return String(decryptCipher.doFinal(bytes), charset("UTF-8"))
}

fun String.encryptAES(key: Key, initVector: ByteArray): String {
    return BaseEncoding.base64().encode(toByteArray().encryptAES(key, initVector))
}

fun ByteArray.encryptAES(key: Key, initVector: ByteArray): ByteArray {
    val iv = IvParameterSpec(initVector)

    val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
    cipher.init(Cipher.ENCRYPT_MODE, key, iv)

    return cipher.doFinal(this)
}

fun String.decryptAES(key: Key, initVector: ByteArray): String {
    return String(BaseEncoding.base64().decode(this).decryptAES(key, initVector))
}

fun ByteArray.decryptAES(key: Key, initVector: ByteArray): ByteArray {
    val iv = IvParameterSpec(initVector)

    val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
    cipher.init(Cipher.DECRYPT_MODE, key, iv)

    return cipher.doFinal(this)
}

fun generateAESKey(): Key {
    val generator = KeyGenerator.getInstance("AES")
    generator.init(128)
    return generator.generateKey()
}

fun generateInitVector(): ByteArray {
    val random = SecureRandom()
    val array = ByteArray(16)
    random.nextBytes(array)
    return array
}

fun String.toAESKey(): Key = SecretKeySpec(this.toByteArray(), "AES")

fun ByteArray.toAESKey(): Key = SecretKeySpec(this, "AES")

fun String.toKey(): PublicKey = BaseEncoding.base64().decode(this).toKey()

fun ByteArray.toKey(): PublicKey {
    try {
        val x509publicKey = X509EncodedKeySpec(this)
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePublic(x509publicKey) as RSAPublicKey
    } catch (e: Exception) {
        throw RuntimeException("Unable to generate key", e)
    }
}

fun String.toPrivateKey(): PrivateKey = BaseEncoding.base64().decode(this).toPrivateKey()

fun ByteArray.toPrivateKey(): PrivateKey {
    try {
        val x509publicKey = PKCS8EncodedKeySpec(this)
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePrivate(x509publicKey) as RSAPrivateKey
    } catch (e: Exception) {
        throw RuntimeException("Unable to generate key", e)
    }
}

fun RSAPublicKey.writeKey(output: DataOutputStream) {
    val objectOut = ObjectOutputStream(output)
    objectOut.writeObject(this.modulus)
    objectOut.writeObject(this.publicExponent)
}

fun readKey(input: DataInputStream): RSAPublicKey {
    val objectIn = ObjectInputStream(input)
    return KeyFactory.getInstance("RSA").generatePublic(RSAPublicKeySpec(objectIn.readObject() as BigInteger, objectIn.readObject() as BigInteger)) as RSAPublicKey
}

fun RSAPrivateKey.writeKey(output: DataOutputStream) {
    val objectOut = ObjectOutputStream(output)
    objectOut.writeObject(this.modulus)
    objectOut.writeObject(this.privateExponent)
}

fun readPrivateKey(input: DataInputStream): RSAPrivateKey {
    val objectIn = ObjectInputStream(input)
    return KeyFactory.getInstance("RSA").generatePrivate(RSAPrivateKeySpec(objectIn.readObject() as BigInteger, objectIn.readObject() as BigInteger)) as RSAPrivateKey
}

fun sha256(input: String): String {
    val digest = Hashing.sha256().hashBytes(input.toByteArray()).asBytes()
    return String.format("%064x", BigInteger(1, digest))
}