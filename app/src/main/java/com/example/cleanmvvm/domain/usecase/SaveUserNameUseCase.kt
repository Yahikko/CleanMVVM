package com.example.cleanmvvm.domain.usecase

import com.example.cleanmvvm.domain.models.SaveUserNameParam
import com.example.cleanmvvm.domain.repository.UserRepository

class SaveUserNameUseCase(private val userRepository: UserRepository) {

    fun execute(param: SaveUserNameParam): Boolean {
        return userRepository.saveName(param)
    }
}