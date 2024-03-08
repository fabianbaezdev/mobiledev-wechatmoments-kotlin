package com.tws.moments.data.api.entry

import com.google.gson.annotations.SerializedName

data class UserBean(
    var username: String? = null,
    var nick: String? = null,
    var avatar: String? = null,
    @SerializedName("profile-image") var profileImage: String?=null
)