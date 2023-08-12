package com.example.cleanmvvm.domain.usecase

import com.example.cleanmvvm.domain.models.UserName
import com.example.cleanmvvm.domain.repository.UserRepository

class GetUserNameUseCase(private val userRepository: UserRepository) {

    fun execute(): UserName {
        return userRepository.getName()
    }
}