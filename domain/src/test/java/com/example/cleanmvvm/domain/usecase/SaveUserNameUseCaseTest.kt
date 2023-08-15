package com.example.cleanmvvm.domain.usecase

import com.example.cleanmvvm.domain.models.SaveUserNameParam
import com.example.cleanmvvm.domain.models.UserName
import com.example.cleanmvvm.domain.repository.UserRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.times

class SaveUserNameUseCaseTest {

    @Mock
    val userRepository = mock<UserRepository>()

    @AfterEach
    fun tearDown() {
        Mockito.reset(userRepository)
    }

    @Test
    fun `should not save data if name was already saved`() {

        val useCase = SaveUserNameUseCase(userRepository = userRepository)

        val testData = UserName(firstName = "John", lastName = "test last name")
        Mockito.`when`(userRepository.getName()).thenReturn(testData)

        val testParams = SaveUserNameParam(name = "John")
        val actual = useCase.execute(testParams)

        val expected = false

        Assertions.assertEquals(expected, actual)
        Mockito.verify(userRepository, never()).saveName(saveParam = any())
    }

    @Test
    fun `should return true when save different names`() {

        val useCase = SaveUserNameUseCase(userRepository = userRepository)

        val testData = UserName(firstName = "John", lastName = "test last name")
        Mockito.`when`(userRepository.getName()).thenReturn(testData)

        val expected = true
        val testParams = SaveUserNameParam("Jack")
        Mockito.`when`(userRepository.saveName(testParams)).thenReturn(expected)

        val actual = useCase.execute(testParams)

        Assertions.assertEquals(expected, actual)
        Mockito.verify(userRepository, times(1)).saveName(saveParam = testParams)
    }

    @Test
    fun `should return false when save different names`() {

        val useCase = SaveUserNameUseCase(userRepository = userRepository)

        val testData = UserName(firstName = "John", lastName = "test last name")
        Mockito.`when`(userRepository.getName()).thenReturn(testData)

        val expected = false
        val testParams = SaveUserNameParam("Jack")
        Mockito.`when`(userRepository.saveName(testParams)).thenReturn(expected)

        val actual = useCase.execute(testParams)

        Assertions.assertEquals(expected, actual)
        Mockito.verify(userRepository, times(1)).saveName(saveParam = testParams)
    }

}