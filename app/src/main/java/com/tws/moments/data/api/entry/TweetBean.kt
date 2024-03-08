package com.tws.moments.data.api.entry

data class TweetBean(
    var content: String? = null,
    var images: List<ImagesBean>? = null,
    var sender: SenderBean? = null,
    var comments: List<CommentsBean>? = null
)