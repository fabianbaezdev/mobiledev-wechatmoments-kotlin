package com.tws.moments.data

import com.tws.moments.data.mapper.TweetMapper
import com.tws.moments.data.mapper.UserMapper
import com.tws.moments.domain.Repository
import com.tws.moments.domain.model.Tweet
import com.tws.moments.domain.model.User
import javax.inject.Inject

class MomentRepository @Inject constructor(
    private val userMapper: UserMapper,
    private val tweetMapper: TweetMapper,
    private val remote: Remote
) : Repository {
    override suspend fun fetchUser(): User {
        return with(userMapper) { remote.fetchUser().remoteToDomain() }
    }

    override suspend fun fetchTweets(): List<Tweet> {
        return with(tweetMapper) {
            remote.fetchTweets()
                .filter { it.sender != null && (it.content != null || !it.images.isNullOrEmpty()) }
                .remoteToDomain()
        }
    }
}
