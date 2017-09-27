package de.intektor.kentai_http_common.chat

import com.google.common.io.BaseEncoding
import de.intektor.kentai_http_common.util.*
import java.security.Key
import java.security.Signature
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.util.*
import javax.crypto.spec.SecretKeySpec

/**
 * @author Intektor
 */
abstract class ChatMessage {

    lateinit var senderUUID: UUID
    /**
     * Every chat message should contain some kind of text. Also used for searches in database on client
     */
    lateinit var text: String
    var timeSent: Long = 0
    var id: UUID = UUID.randomUUID()

    var initVector: String = ""
    var aesKey: String = ""

    var signature: String = ""

    var referenceUUID: UUID = UUID.randomUUID()

    constructor(senderUUID: UUID, text: String, timeSent: Long) {
        this.senderUUID = senderUUID
        this.timeSent = timeSent
        this.text = text
    }

    constructor()

    /**
     * Additional info is used for database storing, all the information that is not defined in ChatMessage should be written in additional info. <br>
     * This gets written into file on the server and can only be gathered by requesting the reference, if it is not small data <br>
     * If this is small data, it will be saved into the database of the server and be sent over when being fetched
     */
    abstract fun processAdditionalInfo(array: ByteArray?)

    abstract fun getAdditionalInfo(): ByteArray?

    fun encrypt(senderPrivate: RSAPrivateKey, receiverPublic: RSAPublicKey) {
        val aKey = generateAESKey()
        val iV = generateInitVector()
        initVector = BaseEncoding.base64().encode(iV)
        text = text.encryptAES(aKey, iV)
        aesKey = BaseEncoding.base64().encode(aKey.encoded).encryptRSA(receiverPublic)

        val signer = Signature.getInstance("SHA1WithRSA")
        signer.initSign(senderPrivate)
        signer.update(iV)
        val signed = signer.sign()
        signature = BaseEncoding.base64().encode(signed)

        initVector = initVector.encryptRSA(receiverPublic)
    }

    fun decrypt(senderPublic: RSAPublicKey, receiverPrivate: RSAPrivateKey) {
        initVector = initVector.decryptRSA(receiverPrivate)
        val iV = BaseEncoding.base64().decode(initVector)

        val signer = Signature.getInstance("SHA1WithRSA")
        signer.initVerify(senderPublic)
        signer.update(iV)
        val signed = BaseEncoding.base64().decode(signature)
        if (!signer.verify(signed)) {
            throw FalseSignatureException()
        }

        aesKey = aesKey.decryptRSA(receiverPrivate)
        val sKey = SecretKeySpec(BaseEncoding.base64().decode(aesKey), "AES")
        text = text.decryptAES(sKey, iV)
    }

    abstract fun encryptMessage(aesKey: Key, initVector: ByteArray)

    abstract fun decryptMessage(aesKey: Key, initVector: ByteArray)

    /**
     * If this message should be written into the clients database
     */
    abstract fun shouldBeStored(): Boolean

    /**
     * @see processAdditionalInfo
     */
    abstract fun isSmallData(): Boolean

    abstract fun hasReference(): Boolean

    fun copy(): ChatMessage {
        val newInstance = ChatMessageRegistry.create(ChatMessageRegistry.getID(javaClass))
        newInstance.aesKey = aesKey
        newInstance.senderUUID = senderUUID
        newInstance.text = text
        newInstance.id = id
        newInstance.initVector = initVector
        newInstance.timeSent = timeSent
        newInstance.signature = signature
        newInstance.referenceUUID = referenceUUID
        newInstance.processAdditionalInfo(getAdditionalInfo())
        return newInstance
    }
}