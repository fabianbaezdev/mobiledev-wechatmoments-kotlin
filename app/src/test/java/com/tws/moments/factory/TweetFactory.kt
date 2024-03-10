package com.tws.moments.factory

import com.tws.moments.data.api.entry.CommentsBean
import com.tws.moments.data.api.entry.ImagesBean
import com.tws.moments.data.api.entry.SenderBean
import com.tws.moments.data.api.entry.TweetBean
import com.tws.moments.domain.model.Comment
import com.tws.moments.domain.model.Tweet

object TweetFactory {

    fun makeTweetBeanWithData() = TweetBean(
        content = "content",
        images = listOf(ImagesBean(url = "url"), ImagesBean(url = "url")),
        sender = SenderBean(
            username = "username",
            nick = "nick",
            avatar = "avatar"
        ),
        comments = listOf(
            CommentsBean(
                content = "content", sender = SenderBean(
                    username = "username",
                    nick = "nick",
                    avatar = "avatar"
                )
            ), CommentsBean(
                content = "content", sender = SenderBean(
                    username = "username",
                    nick = "nick",
                    avatar = "avatar"
                )
            )
        )
    )

    fun makeTweetBeanWithoutComments() = TweetBean(
        content = "content",
        images = listOf(ImagesBean(url = "url1"), ImagesBean(url = "url2")),
        sender = SenderBean(
            username = "username",
            nick = "nick",
            avatar = "avatar"
        )
    )

    fun makeTweetBeanWithoutData() = TweetBean()

    fun makeTweetWithData() = Tweet(
        content = "content",
        images = listOf("url", "url"),
        senderUsername = "username",
        senderNick = "nick",
        senderAvatar = "avatar",
        comments = listOf(
            Comment(
                content = "content",
                senderUsername = "username",
                senderNick = "nick",
                senderAvatar = "avatar"

            ), Comment(
                content = "content",
                senderUsername = "username",
                senderNick = "nick",
                senderAvatar = "avatar"
            )
        )
    )
}