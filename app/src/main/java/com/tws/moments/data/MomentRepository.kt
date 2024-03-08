package com.tws.moments.data

import com.tws.moments.data.api.entry.TweetBean
import com.tws.moments.data.api.entry.UserBean
import com.tws.moments.data.api.reqApi
import com.tws.moments.domain.Repository

class MomentRepository: Repository {
    override suspend fun fetchUser(): UserBean {
        return reqApi.user("jsmith")
    }

    override suspend fun fetchTweets(): List<TweetBean> {
        return reqApi.tweets("jsmith")
    }
}
