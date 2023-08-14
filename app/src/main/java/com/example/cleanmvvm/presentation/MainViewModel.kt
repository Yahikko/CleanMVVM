package com.example.cleanmvvm.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleanmvvm.domain.models.SaveUserNameParam
import com.example.cleanmvvm.domain.models.UserName
import com.example.cleanmvvm.domain.usecase.GetUserNameUseCase
import com.example.cleanmvvm.domain.usecase.SaveUserNameUseCase

class MainViewModel(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase
) : ViewModel() {

    private var stateMutableLive = MutableLiveData<MainState>()
    val stateLive: LiveData<MainState> = stateMutableLive

    init {
        Log.e("AAA", "VM created")
        stateMutableLive.value = MainState(
            saveResult = false,
            firstName = "",
            lastName = ""
        )
    }

    override fun onCleared() {
        Log.e("AAA", "VM cleared")
        super.onCleared()
    }

    fun send(event: MainEvent) {
        when (event) {
            is SaveEvent -> save(event.text)
            is LoadEvent -> load()
        }
    }

    private fun save(text: String) {
        val param = SaveUserNameParam(name = text)
        val result = saveUserNameUseCase.execute(param)
        stateMutableLive.value = MainState(
            saveResult = result,
            firstName = stateMutableLive.value!!.firstName,
            lastName = stateMutableLive.value!!.lastName
        )
    }

    private fun load() {
        val userName: UserName = getUserNameUseCase.execute()
        stateMutableLive.value = MainState(
            saveResult = stateMutableLive.value!!.saveResult,
            firstName = userName.firstName,
            lastName = userName.lastName
        )
    }
}