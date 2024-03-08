package com.tws.moments.factory

import com.tws.moments.data.api.entry.UserBean

object UserFactory {
    fun makeUserBeanWithData() = UserBean(
        username = "username",
        nick = "nick",
        avatar = "avatar",
        profileImage = "profileImage"
    )

    fun makeUserBeanWithoutData() = UserBean()
}