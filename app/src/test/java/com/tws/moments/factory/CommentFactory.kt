package com.tws.moments.factory

import com.tws.moments.data.api.entry.CommentsBean
import com.tws.moments.data.api.entry.SenderBean

object CommentFactory {

    fun makeCommentsBeanWithData() = CommentsBean(
        content = "content", sender = SenderBean(
            username = "username",
            nick = "nick",
            avatar = "avatar"
        )
    )

    fun makeCommentsWithoutSender() = CommentsBean(
        content = "content"
    )

    fun makeCommentsWithoutData() = CommentsBean()

}