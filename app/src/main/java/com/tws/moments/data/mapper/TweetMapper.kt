package com.tws.moments.data.mapper

import com.tws.moments.data.api.entry.TweetBean
import com.tws.moments.domain.model.Tweet
import javax.inject.Inject

class TweetMapper @Inject constructor(private val commentMapper: CommentMapper) {
    fun List<TweetBean>.remoteToDomain() = this.map { it.remoteToDomain() }

    fun TweetBean.remoteToDomain() =
        Tweet(
            content = content.orEmpty(),
            images = images?.filter { !it.url.isNullOrEmpty() }.orEmpty().map { it.url!! },
            senderUsername = sender?.username.orEmpty(),
            senderNick = sender?.nick.orEmpty(),
            senderAvatar = sender?.avatar.orEmpty(),
            comments = with(commentMapper) { comments?.remoteToDomain() }.orEmpty()
        )
}