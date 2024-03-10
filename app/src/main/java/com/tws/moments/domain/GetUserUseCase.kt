package com.tws.moments.domain

import com.tws.moments.domain.model.User
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repository: Repository) {
    suspend fun execute(): User {
        return repository.fetchUser()
    }
}