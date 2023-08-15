package com.example.cleanmvvm.domain.usecase

import com.example.cleanmvvm.domain.models.SaveUserNameParam
import com.example.cleanmvvm.domain.repository.UserRepository

class SaveUserNameUseCase(private val userRepository: UserRepository) {

    fun execute(param: SaveUserNameParam): Boolean {

        val oldUserName = userRepository.getName()

        if (param.name.isEmpty() || oldUserName.firstName == param.name) {
            return false
        }

        return userRepository.saveName(saveParam = param)
    }
}