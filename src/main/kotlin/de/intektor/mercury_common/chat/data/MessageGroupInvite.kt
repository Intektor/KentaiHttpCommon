package de.intektor.mercury_common.chat.data

import de.intektor.mercury_common.chat.GroupRole
import de.intektor.mercury_common.chat.MessageData
import java.security.Key
import java.util.*

/**
 * @author Intektor
 */
class MessageGroupInvite(val groupInvite: GroupInvite) : MessageData {

    abstract class GroupInvite(val chatUUID: UUID, val groupName: String, val groupKey: Key)

    class GroupInviteDecentralizedChat(val roleMap: HashMap<UUID, GroupRole>, chatUUID: UUID, groupName: String, groupKey: Key) : GroupInvite(chatUUID, groupName, groupKey)

    class GroupInviteCentralizedChat(chatUUID: UUID, groupName: String, groupKey: Key) : GroupInvite(chatUUID, groupName, groupKey)
}