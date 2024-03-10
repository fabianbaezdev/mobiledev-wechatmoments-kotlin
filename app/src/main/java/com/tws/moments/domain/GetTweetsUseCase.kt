package com.tws.moments.domain

import com.tws.moments.domain.model.Tweet
import javax.inject.Inject

class GetTweetsUseCase @Inject constructor(private val repository: Repository) {
    suspend fun execute(): List<Tweet> {
        return repository.fetchTweets()
    }
}