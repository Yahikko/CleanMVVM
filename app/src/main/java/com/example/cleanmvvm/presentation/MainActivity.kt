package com.example.cleanmvvm.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cleanmvvm.R

class MainActivity : AppCompatActivity() {


    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("AAA", "Activity created")

        vm = ViewModelProvider(this, MainViewModelFactory(this))
            .get(MainViewModel::class.java)

        val dataTextView: TextView = findViewById(R.id.dataTextView)
        val dataEditText: EditText = findViewById(R.id.dataEditText)
        val sendButton: Button = findViewById(R.id.sendButton)
        val receiveButton: Button = findViewById(R.id.receiveButton)

        vm.resultLive.observe(this) {
            dataTextView.text = it
        }

        sendButton.setOnClickListener {
            val text = dataEditText.text.toString()
            vm.save(text)
        }

        receiveButton.setOnClickListener {
            vm.load()
        }
    }
}