package com.tws.moments.factory

import com.tws.moments.data.api.entry.UserBean
import com.tws.moments.domain.model.User

object UserFactory {
    fun makeUserBeanWithData() = UserBean(
        username = "username",
        nick = "nick",
        avatar = "avatar",
        profileImage = "profileImage"
    )

    fun makeUserBeanWithoutData() = UserBean()

    fun makeUserWithData() = User(
        username = "username",
        nick = "nick",
        avatar = "avatar",
        profileImage = "profileImage"
    )
}