package de.intektor.mercury_common.chat.data.group_modification

import java.util.*

/**
 * @author Intektor
 */
class GroupModificationKickUser(val kickedUser: UUID, val reason: String, chatUUID: UUID, modificationUUID: UUID) : GroupModification(chatUUID, modificationUUID)