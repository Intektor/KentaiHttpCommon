package de.intektor.mercury_common.chat.data.group_modification

import de.intektor.mercury_common.chat.GroupRole
import java.util.*

/**
 * @author Intektor
 */
class GroupModificationChangeRole(val affectedUser: UUID, val oldRole: GroupRole, val newRole: GroupRole, chatUUID: UUID, modificationUUID: UUID) : GroupModification(chatUUID, modificationUUID)