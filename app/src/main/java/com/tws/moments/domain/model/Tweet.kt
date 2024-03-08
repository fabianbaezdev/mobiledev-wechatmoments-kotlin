package com.tws.moments.domain.model

data class Tweet(
    val content: String,
    val images: List<String>,
    val senderUsername: String,
    val senderNick: String,
    val senderAvatar: String,
    val comments: List<Comment>
)
