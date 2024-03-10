package com.tws.moments.data.mapper

import com.tws.moments.data.api.entry.UserBean
import com.tws.moments.domain.model.User
import javax.inject.Inject

class UserMapper @Inject constructor() {
    fun UserBean.remoteToDomain() =
        User(
            username = username.orEmpty(),
            nick = nick.orEmpty(),
            avatar = avatar.orEmpty(),
            profileImage = profileImage.orEmpty()
        )

}