package com.example.cleanmvvm.di

import com.example.cleanmvvm.domain.usecase.GetUserNameUseCase
import com.example.cleanmvvm.domain.usecase.SaveUserNameUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetUserNameUseCase(userRepository = get())
    }

    factory {
        SaveUserNameUseCase(userRepository = get())
    }
}