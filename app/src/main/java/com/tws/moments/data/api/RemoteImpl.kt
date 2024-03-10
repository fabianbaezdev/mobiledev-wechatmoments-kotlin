package com.tws.moments.data.api

import com.tws.moments.data.Remote
import com.tws.moments.data.api.entry.TweetBean
import com.tws.moments.data.api.entry.UserBean
import javax.inject.Inject

class RemoteImpl @Inject constructor(private val webservice: MomentService) : Remote {
    private val user = "jsmith"
    override suspend fun fetchUser(): UserBean = webservice.user(user)

    override suspend fun fetchTweets(): List<TweetBean> = webservice.tweets(user)
}