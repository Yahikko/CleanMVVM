package com.example.cleanmvvm.di

import com.example.cleanmvvm.presentation.MainPresenterIml
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainPresenterIml> {
        MainPresenterIml(
            getUserNameUseCase = get(),
            saveUserNameUseCase = get()
        )
    }
}