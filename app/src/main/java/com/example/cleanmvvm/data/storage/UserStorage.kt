package com.example.cleanmvvm.data.storage

import com.example.cleanmvvm.data.storage.models.User

interface UserStorage {

    fun save(user: User): Boolean

    fun get(): User
}