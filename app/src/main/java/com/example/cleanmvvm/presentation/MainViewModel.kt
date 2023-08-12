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

    private var resultMutableLive = MutableLiveData<String>()
    val resultLive: LiveData<String> = resultMutableLive

    init {
        Log.e("AAA", "VM created")
    }

    override fun onCleared() {
        Log.e("AAA", "VM cleared")
        super.onCleared()
    }

    fun save(text: String) {
        val param = SaveUserNameParam(name = text)
        val result = saveUserNameUseCase.execute(param)
        resultMutableLive.value = "Save result = $result"
    }

    fun load() {
        val userName: UserName = getUserNameUseCase.execute()
        resultMutableLive.value = "${userName.firstName} ${userName.lastName}"
    }
}