package com.tws.moments.domain.model

data class Comment(
    val content: String,
    val senderUsername: String,
    val senderNick: String,
    val senderAvatar: String
)
