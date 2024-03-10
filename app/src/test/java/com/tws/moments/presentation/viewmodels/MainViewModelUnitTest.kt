package com.tws.moments.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tws.moments.domain.GetTweetsUseCase
import com.tws.moments.domain.GetUserUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.util.concurrent.Executors

@ExperimentalCoroutinesApi
class MainViewModelUnitTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun test_fetch_tweets() {
        val getTweetsUseCase = mockk<GetTweetsUseCase>()
        val getUserUseCase = mockk<GetUserUseCase>()
        coEvery {
            getTweetsUseCase.execute()
        } returns listOf()

        val mainViewModel = MainViewModel(getUserUseCase, getTweetsUseCase)
        mainViewModel.refreshTweets()

        Assert.assertEquals(0, mainViewModel.tweets.value?.size?:0)
    }
}