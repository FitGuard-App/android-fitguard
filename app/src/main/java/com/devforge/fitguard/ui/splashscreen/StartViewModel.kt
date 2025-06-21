package com.devforge.fitguard.ui.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devforge.fitguard.data.UserEntity
import com.devforge.fitguard.data.UserRepository
import kotlinx.coroutines.launch

class StartViewModel(private val userRepository: UserRepository): ViewModel() {
    fun getUser(): LiveData<UserEntity> {
        return  userRepository.getUser()
    }

    fun insert(user: UserEntity) {
        viewModelScope.launch {
            userRepository.insertUser(user)
        }
    }
}