package com.tws.moments.domain

import com.tws.moments.data.api.entry.TweetBean
import com.tws.moments.data.api.entry.UserBean

interface Repository {
    suspend fun fetchUser(): UserBean
    suspend fun fetchTweets(): List<TweetBean>
}