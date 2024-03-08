package com.tws.moments.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tws.moments.data.MomentRepository
import com.tws.moments.domain.model.Tweet
import com.tws.moments.domain.model.User
import kotlinx.coroutines.launch
import kotlin.math.min

private const val TAG = "MainViewModel##"

private const val PAGE_TWEET_COUNT = 5

class MainViewModel(private val repository: MomentRepository) : ViewModel() {

    val userBean: MutableLiveData<User> by lazy {
        MutableLiveData<User>().also { loadUserInfo() }
    }

    val tweets: MutableLiveData<List<Tweet>> by lazy {
        MutableLiveData<List<Tweet>>().also { loadTweets() }
    }

    private var allTweets: List<Tweet> = emptyList()


    private fun loadUserInfo() {
        viewModelScope.launch {
            val result = try {
                repository.fetchUser()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
            userBean.value = result
        }
    }


    private fun loadTweets() {
        viewModelScope.launch {
            val result = try {
                repository.fetchTweets()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }

            allTweets = result.orEmpty()

            if ((allTweets.size) > PAGE_TWEET_COUNT) {
                tweets.value = allTweets.subList(0, PAGE_TWEET_COUNT)
            } else {
                tweets.value = allTweets
            }
        }
    }

    fun refreshTweets() {
        loadTweets()
    }

    val pageCount: Int
        get() {
            return when {
                allTweets.isEmpty() -> 0
                allTweets.size % PAGE_TWEET_COUNT == 0 -> allTweets.size / PAGE_TWEET_COUNT
                else -> allTweets.size / PAGE_TWEET_COUNT + 1
            }
        }

    fun loadMoreTweets(pageIndex: Int, onLoad: (List<Tweet>) -> Unit) {
        if (pageIndex < 0) {
            throw IllegalArgumentException("page index must greater than or equal to 0.")
        }

        if (pageIndex > pageCount - 1) {
            return
        }

        viewModelScope.launch {
            val startIndex = PAGE_TWEET_COUNT * pageIndex
            val endIndex = min(allTweets.size, PAGE_TWEET_COUNT * (pageIndex + 1))
            val result = allTweets.subList(startIndex, endIndex)
            onLoad(result)
        }
    }

}
