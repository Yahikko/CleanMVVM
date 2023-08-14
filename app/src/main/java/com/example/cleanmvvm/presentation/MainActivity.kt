package com.example.cleanmvvm.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cleanmvvm.R
import com.example.cleanmvvm.data.repository.UserRepositoryImpl
import com.example.cleanmvvm.data.storage.sharedpref.SharedPrefUserStorage
import com.example.cleanmvvm.domain.repository.UserRepository
import com.example.cleanmvvm.domain.usecase.GetUserNameUseCase
import com.example.cleanmvvm.domain.usecase.SaveUserNameUseCase
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), MainView {

    private val sharedPrefUserStorage by lazy(LazyThreadSafetyMode.NONE) {
        SharedPrefUserStorage(this)
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

    // presenter should be created with DI and MOXY(?)
    private val presenter: MainPresenter = MainPresenterIml(
        getUserNameUseCase = getUserNameUseCase,
        saveUserNameUseCase = saveUserNameUseCase,
        view = this
    )

    lateinit var dataTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("AAA", "Activity created")

        dataTextView = findViewById(R.id.dataTextView)
        val dataEditText: EditText = findViewById(R.id.dataEditText)
        val sendButton: Button = findViewById(R.id.sendButton)
        val receiveButton: Button = findViewById(R.id.receiveButton)

        sendButton.setOnClickListener {
            val text = dataEditText.text.toString()
            presenter.save(text)
        }

        receiveButton.setOnClickListener {
            presenter.load()
        }
    }

    override fun showResults(text: String) {
        dataTextView.text = text
    }
}