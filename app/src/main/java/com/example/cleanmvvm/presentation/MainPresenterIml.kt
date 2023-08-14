package com.example.cleanmvvm.presentation

import android.util.Log
import com.example.cleanmvvm.domain.models.SaveUserNameParam
import com.example.cleanmvvm.domain.models.UserName
import com.example.cleanmvvm.domain.usecase.GetUserNameUseCase
import com.example.cleanmvvm.domain.usecase.SaveUserNameUseCase

class MainPresenterIml(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase,
    private val view: MainView
) : MainPresenter {

    init {
        Log.e("AAA", "VM created")
    }

    override fun save(text: String) {
        val param = SaveUserNameParam(name = text)
        val result = saveUserNameUseCase.execute(param)
        view.showResults("Save result = $result")
    }

    override fun load() {
        val userName: UserName = getUserNameUseCase.execute()
        view.showResults("${userName.firstName} ${userName.lastName}")
    }
}