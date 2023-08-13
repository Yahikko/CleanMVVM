package com.example.cleanmvvm.di

import com.example.cleanmvvm.data.repository.UserRepositoryImpl
import com.example.cleanmvvm.data.storage.UserStorage
import com.example.cleanmvvm.data.storage.sharedpref.SharedPrefUserStorage
import com.example.cleanmvvm.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {

    single<UserStorage> {
        SharedPrefUserStorage(context = get())
    }

    single<UserRepository> {
        UserRepositoryImpl(userStorage = get())
    }
}