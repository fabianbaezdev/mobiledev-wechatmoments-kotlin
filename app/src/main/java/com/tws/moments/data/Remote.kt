package com.tws.moments.data

import com.tws.moments.data.api.entry.TweetBean
import com.tws.moments.data.api.entry.UserBean

interface Remote {

    suspend fun fetchUser(): UserBean
    suspend fun fetchTweets(): List<TweetBean>
}