package de.intektor.kentai_http_common.util

import com.google.common.hash.Hashing
import com.google.common.io.BaseEncoding
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.math.BigInteger
import java.security.Key
import java.security.KeyFactory
import java.security.SecureRandom
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
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
    val iv = IvParameterSpec(initVector)

    val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
    cipher.init(Cipher.ENCRYPT_MODE, key, iv)

    val encrypted = cipher.doFinal(this.toByteArray())

    return BaseEncoding.base64().encode(encrypted)
}

fun String.decryptAES(key: Key, initVector: ByteArray): String {
    val iv = IvParameterSpec(initVector)

    val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
    cipher.init(Cipher.DECRYPT_MODE, key, iv)

    val original = cipher.doFinal(BaseEncoding.base64().decode(this))

    return String(original)
}

fun generateAESKey(): Key {
    val generator = KeyGenerator.getInstance("AES")
    generator.init(256)
    return generator.generateKey()
}

fun generateInitVector(): ByteArray {
    val random = SecureRandom()
    val array = ByteArray(16)
    random.nextBytes(array)
    return array
}

fun String.toAESKey(): Key = SecretKeySpec(this.toByteArray(), "AES")

fun String.toKey(): Key {
    try {
        val byteKey = BaseEncoding.base64().decode(this)
        val X509publicKey = X509EncodedKeySpec(byteKey)
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePublic(X509publicKey) as RSAPublicKey
    } catch (e: Exception) {
        e.printStackTrace()
    }
    throw RuntimeException()
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