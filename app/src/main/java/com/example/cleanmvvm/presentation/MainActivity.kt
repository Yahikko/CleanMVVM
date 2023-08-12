package com.example.cleanmvvm.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cleanmvvm.R
import com.example.cleanmvvm.data.repository.UserRepositoryImpl
import com.example.cleanmvvm.data.storage.sharedpref.SharedPrefUserStorage
import com.example.cleanmvvm.domain.models.SaveUserNameParam
import com.example.cleanmvvm.domain.models.UserName
import com.example.cleanmvvm.domain.usecase.GetUserNameUseCase
import com.example.cleanmvvm.domain.usecase.SaveUserNameUseCase

class MainActivity : AppCompatActivity() {

    private val sharedPrefUserStorage by lazy(LazyThreadSafetyMode.NONE) {
        SharedPrefUserStorage(applicationContext)
    }
    private val userRepository by lazy(LazyThreadSafetyMode.NONE) {
        UserRepositoryImpl(sharedPrefUserStorage)
    }
    private val getUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetUserNameUseCase(userRepository)
    }
    private val saveUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveUserNameUseCase(userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataTextView: TextView = findViewById(R.id.dataTextView)
        val dataEditText: EditText = findViewById(R.id.dataEditText)
        val sendButton: Button = findViewById(R.id.sendButton)
        val receiveButton: Button = findViewById(R.id.receiveButton)

        sendButton.setOnClickListener {
            val data = dataEditText.text.toString()
            val param = SaveUserNameParam(data)
            val result = saveUserNameUseCase.execute(param)
            dataTextView.text = "Save result = $result"
        }

        receiveButton.setOnClickListener {
            val userName: UserName = getUserNameUseCase.execute()
            dataTextView.text = "${userName.firstName} ${userName.lastName}"
        }
    }
}