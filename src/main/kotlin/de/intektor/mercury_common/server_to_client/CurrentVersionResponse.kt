package de.intektor.mercury_common.server_to_client

/**
 * The first element in the list is the most recent update
 * @author Intektor
 */
class CurrentVersionResponse(val changes: List<ChangeLog>) {

    data class ChangeLog(val versionCode: Long, val versionName: String, val changes: List<Change>, val downloadLink: String)

    data class Change(val text: String, val changeType: ChangeType)

    enum class ChangeType {
        ADDITION,
        REMOVAL,
        FIX,
        TWEAK
    }
}