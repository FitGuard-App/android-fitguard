package com.devforge.fitguard.utils

import android.content.Context
import com.devforge.fitguard.data.UserDatabase
import com.devforge.fitguard.data.UserRepository

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val database = UserDatabase.getInstance(context)
        val dao = database.userDao()
        return UserRepository.getInstance(dao)
    }
}