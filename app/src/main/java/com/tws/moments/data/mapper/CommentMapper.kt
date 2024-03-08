package com.tws.moments.data.mapper

import com.tws.moments.data.api.entry.CommentsBean
import com.tws.moments.domain.model.Comment

class CommentMapper {

    fun List<CommentsBean>?.remoteToDomain() = this?.map { it.remoteToDomain() }

    fun CommentsBean.remoteToDomain() =
        Comment(
            content = content.orEmpty(),
            senderUsername = sender?.username.orEmpty(),
            senderNick = sender?.nick.orEmpty(),
            senderAvatar = sender?.avatar.orEmpty()
        )

}