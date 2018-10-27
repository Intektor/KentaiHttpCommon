package de.intektor.mercury_common.chat.data.group_modification

import java.util.*

/**
 * @author Intektor
 */
class GroupModificationAddUser(val addedUser: UUID, chatUUID: UUID, modificationUUID: UUID) : GroupModification(chatUUID, modificationUUID)