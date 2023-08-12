package com.example.cleanmvvm.data.storage.sharedpref

import android.content.Context
import com.example.cleanmvvm.data.DEFAULT_FIRST_NAME
import com.example.cleanmvvm.data.DEFAULT_LAST_NAME
import com.example.cleanmvvm.data.KEY_FIRST_NAME
import com.example.cleanmvvm.data.KEY_LAST_NAME
import com.example.cleanmvvm.data.SHARED_PREFS_NAME
import com.example.cleanmvvm.data.storage.UserStorage
import com.example.cleanmvvm.data.storage.models.User

class SharedPrefUserStorage(context: Context) : UserStorage {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun save(user: User): Boolean {
        sharedPreferences.edit().putString(KEY_FIRST_NAME, user.firstName).apply()
        sharedPreferences.edit().putString(KEY_LAST_NAME, user.lastName).apply()
        return true
    }

    override fun get(): User {
        val firstName = sharedPreferences.getString(KEY_FIRST_NAME, DEFAULT_FIRST_NAME) ?: DEFAULT_FIRST_NAME
        val lastName = sharedPreferences.getString(KEY_LAST_NAME, DEFAULT_LAST_NAME) ?: DEFAULT_LAST_NAME
        return User(firstName = firstName, lastName = lastName)
    }
}