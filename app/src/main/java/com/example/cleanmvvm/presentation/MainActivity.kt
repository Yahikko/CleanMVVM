package com.example.cleanmvvm.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cleanmvvm.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    private val vm by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("AAA", "Activity created")

        val dataTextView: TextView = findViewById(R.id.dataTextView)
        val dataEditText: EditText = findViewById(R.id.dataEditText)
        val sendButton: Button = findViewById(R.id.sendButton)
        val receiveButton: Button = findViewById(R.id.receiveButton)

        vm.stateLive.observe(this) { state ->
            dataTextView.text = "${state.firstName} ${state.lastName} ${state.saveResult}"
        }

        sendButton.setOnClickListener {
            val text = dataEditText.text.toString()
            vm.send(SaveEvent(text = text))
        }

        receiveButton.setOnClickListener {
            vm.send(LoadEvent())
        }
    }
}