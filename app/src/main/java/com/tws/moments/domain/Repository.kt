package com.tws.moments.domain

import com.tws.moments.domain.model.Tweet
import com.tws.moments.domain.model.User

interface Repository {
    suspend fun fetchUser(): User
    suspend fun fetchTweets(): List<Tweet>
}