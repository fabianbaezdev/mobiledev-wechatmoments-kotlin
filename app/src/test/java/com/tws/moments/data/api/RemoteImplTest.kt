package com.tws.moments.data.api

import com.tws.moments.factory.TweetFactory.makeTweetBeanWithData
import com.tws.moments.factory.UserFactory.makeUserBeanWithData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class RemoteImplTest {
    private val webService = mockk<MomentService>()
    private val remote = RemoteImpl(webService)

    @Test
    fun `given UserBean, when fetchUser, the return data`() = runBlocking {
        val userBean = makeUserBeanWithData()
        coEvery { webService.user("jsmith") } returns userBean

        val result = remote.fetchUser()

        assertEquals(userBean, result)
    }

    @Test
    fun `given TweetBean, when fetchTweets, the return data`() = runBlocking {
        val tweetBean = listOf(makeTweetBeanWithData())
        coEvery { webService.tweets("jsmith") } returns tweetBean

        val result = remote.fetchTweets()

        assertEquals(tweetBean, result)
    }
}