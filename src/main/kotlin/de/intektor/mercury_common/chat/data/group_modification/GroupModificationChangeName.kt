package de.intektor.mercury_common.chat.data.group_modification

import java.util.*

/**
 * @author Intektor
 */
class GroupModificationChangeName(val newName: String, val oldName: String, chatUUID: UUID, modificationUUID: UUID) : GroupModification(chatUUID, modificationUUID)