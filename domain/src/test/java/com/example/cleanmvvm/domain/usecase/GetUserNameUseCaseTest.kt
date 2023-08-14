package com.example.cleanmvvm.domain.usecase

import com.example.cleanmvvm.domain.models.UserName
import com.example.cleanmvvm.domain.repository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetUserNameUseCaseTest {

    @Mock
    val userRepository = mock<UserRepository>()

    @Test
    fun `should return the same as in repository`() {

        val testData = UserName(firstName = "test first name", lastName = "test last name")

        Mockito.`when`(userRepository.getName())
            .thenReturn(testData)

        val useCase = GetUserNameUseCase(userRepository = userRepository)
        val actual = useCase.execute()

        val expected = UserName(firstName = "test first name", lastName = "test last name")

        Assertions.assertEquals(expected, actual)
    }
}