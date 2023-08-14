package com.example.cleanmvvm.domain.usecase

import com.example.cleanmvvm.domain.models.SaveUserNameParam
import com.example.cleanmvvm.domain.models.UserName
import com.example.cleanmvvm.domain.repository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestRepository : UserRepository {
    override fun saveName(saveParam: SaveUserNameParam): Boolean {
        TODO("Not yet implemented")
    }

    override fun getName(): UserName {
        return UserName(firstName = "test first name", lastName = "test last name")
    }
}

class GetUserNameUseCaseTest {

    @Test
    fun `should return the same as in repository`() {

        val testRepository = TestRepository()
        val useCase = GetUserNameUseCase(userRepository = testRepository)
        val actual = useCase.execute()

        val expected = UserName(firstName = "test first name", lastName = "test last name")

        Assertions.assertEquals(expected, actual)
    }
}