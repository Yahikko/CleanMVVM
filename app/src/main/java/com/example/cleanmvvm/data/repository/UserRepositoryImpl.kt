package com.example.cleanmvvm.data.repository

import com.example.cleanmvvm.data.DEFAULT_LAST_NAME
import com.example.cleanmvvm.data.storage.UserStorage
import com.example.cleanmvvm.data.storage.models.User
import com.example.cleanmvvm.domain.models.SaveUserNameParam
import com.example.cleanmvvm.domain.models.UserName
import com.example.cleanmvvm.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {

    override fun saveName(saveParam: SaveUserNameParam): Boolean {
        val user = mapToStorage(saveParam)
        return userStorage.save(user)
    }

    override fun getName(): UserName {
        val user = userStorage.get()
        return matToDomain(user)
    }

    private fun mapToStorage(saveParam: SaveUserNameParam): User {
        return User(firstName = saveParam.name, lastName = DEFAULT_LAST_NAME)
    }

    private fun matToDomain(user: User): UserName {
        return UserName(firstName = user.firstName, lastName = user.lastName)
    }
}