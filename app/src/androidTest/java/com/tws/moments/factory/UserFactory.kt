package com.tws.moments.factory

import com.tws.moments.domain.model.User

object UserFactory {
    fun makeUserWithData() = User(
        username = "username",
        nick = "nick",
        avatar = "avatar",
        profileImage = "profileImage"
    )
}