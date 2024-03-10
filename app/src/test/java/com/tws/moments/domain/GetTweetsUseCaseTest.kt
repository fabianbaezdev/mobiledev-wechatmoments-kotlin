package com.tws.moments.domain

import com.tws.moments.factory.TweetFactory.makeTweetWithData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetTweetsUseCaseTest {
    private val repository = mockk<Repository>()
    private val useCase = GetTweetsUseCase(repository)

    @Test
    fun `when calls 'execute', the returns list of Tweet`() = runBlocking {
        val tweets = listOf(makeTweetWithData())
        coEvery { repository.fetchTweets() } returns tweets

        val result = useCase.execute()

        assertEquals(tweets, result)

        coVerify { repository.fetchTweets() }
    }

}