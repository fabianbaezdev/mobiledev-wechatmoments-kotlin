package com.tws.moments.domain

import com.tws.moments.factory.UserFactory.makeUserWithData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetUserUseCaseTest {
    private val repository = mockk<Repository>()
    private val useCase = GetUserUseCase(repository)

    @Test
    fun `when calls 'execute', the returns list of Tweet`() = runBlocking {
        val user = makeUserWithData()
        coEvery { repository.fetchUser() } returns user

        val result = useCase.execute()

        assertEquals(user, result)

        coVerify { repository.fetchUser() }
    }
}