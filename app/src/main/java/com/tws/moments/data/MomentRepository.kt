package com.tws.moments.data

import com.tws.moments.data.api.reqApi
import com.tws.moments.data.mapper.TweetMapper
import com.tws.moments.data.mapper.UserMapper
import com.tws.moments.domain.Repository
import com.tws.moments.domain.model.Tweet
import com.tws.moments.domain.model.User

class MomentRepository : Repository {

    private val userMapper = UserMapper()
    private val tweetMapper = TweetMapper()
    override suspend fun fetchUser(): User {
        return with(userMapper) { reqApi.user("jsmith").remoteToDomain() }
    }

    override suspend fun fetchTweets(): List<Tweet> {
        return with(tweetMapper) {
            reqApi.tweets("jsmith")
                .filter { it.sender != null && (it.content != null || !it.images.isNullOrEmpty()) }
                .remoteToDomain()
        }
    }
}
