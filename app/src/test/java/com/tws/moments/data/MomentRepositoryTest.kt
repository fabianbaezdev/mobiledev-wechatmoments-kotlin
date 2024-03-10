package com.tws.moments.data

import com.tws.moments.data.mapper.TweetMapper
import com.tws.moments.data.mapper.UserMapper
import com.tws.moments.factory.TweetFactory.makeTweetBeanWithData
import com.tws.moments.factory.TweetFactory.makeTweetWithData
import com.tws.moments.factory.UserFactory.makeUserBeanWithData
import com.tws.moments.factory.UserFactory.makeUserWithData
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class MomentRepositoryTest {
    private val remote = mockk<Remote>()
    private val userMapper = mockk<UserMapper>()
    private val tweetMapper = mockk<TweetMapper>()
    private val repository = MomentRepository(
        remote = remote,
        userMapper = userMapper,
        tweetMapper = tweetMapper
    )

    @Test
    fun `given UserBean, when fetchUser, the return data`() = runBlocking {
        val userBean = makeUserBeanWithData()
        val user = makeUserWithData()

        coEvery { remote.fetchUser() } returns userBean
        every { with(userMapper) { userBean.remoteToDomain() } } returns user

        val result = repository.fetchUser()

        Assert.assertEquals(user, result)
    }

    @Test
    fun `given TweetBean, when fetchTweets, the return data`() = runBlocking {
        val tweetBeans = listOf(makeTweetBeanWithData())
        val tweets = listOf(makeTweetWithData())

        coEvery { remote.fetchTweets() } returns tweetBeans
        every { with(tweetMapper) { tweetBeans.remoteToDomain() } } returns tweets

        val result = repository.fetchTweets()

        Assert.assertEquals(tweets, result)
    }


}