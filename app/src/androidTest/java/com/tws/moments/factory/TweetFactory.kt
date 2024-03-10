package com.tws.moments.factory

import com.tws.moments.domain.model.Comment
import com.tws.moments.domain.model.Tweet

object TweetFactory {
    fun makeTweetWithData() = Tweet(
        content = "content",
        images = listOf(
            "https://techops-recsys-lateral-hiring.github.io/moments-data/images/tweets/001.jpeg",
            "https://techops-recsys-lateral-hiring.github.io/moments-data/images/tweets/001.jpeg"
        ),
        senderUsername = "username",
        senderNick = "nick",
        senderAvatar = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/tweets/001.jpeg",
        comments = listOf(
            Comment(
                content = "content",
                senderUsername = "username",
                senderNick = "nick",
                senderAvatar = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/tweets/001.jpeg"

            ), Comment(
                content = "content",
                senderUsername = "username",
                senderNick = "nick",
                senderAvatar = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/tweets/001.jpeg"
            )
        )
    )

    fun makeTweetWithMissingData() = Tweet(
        content = "",
        images = listOf("https://techops-recsys-lateral-hiring.github.io/moments-data/images/tweets/001.jpeg"),
        senderUsername = "username",
        senderNick = "nick",
        senderAvatar = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/tweets/001.jpeg",
        comments = emptyList()
    )
}