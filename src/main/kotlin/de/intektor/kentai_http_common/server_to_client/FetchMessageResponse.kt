package de.intektor.kentai_http_common.server_to_client

/**
 * @author Intektor
 */
class FetchMessageResponse(val text: String, val reference: String?, val timeSent: Long, val aesKey: String, val initVector: String)