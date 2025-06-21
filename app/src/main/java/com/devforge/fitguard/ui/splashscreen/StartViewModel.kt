package com.devforge.fitguard.ui.splashscreen

import androidx.lifecycle.ViewModel
import com.devforge.fitguard.data.UserEntity
import com.devforge.fitguard.data.UserRepository

class StartViewModel(private val userRepository: UserRepository): ViewModel() {
    fun getUser(): UserEntity {
        return  userRepository.getUser()
    }
}