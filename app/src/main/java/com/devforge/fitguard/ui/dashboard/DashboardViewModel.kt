package com.devforge.fitguard.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.devforge.fitguard.data.RecordEntity
import com.devforge.fitguard.data.UserEntity
import com.devforge.fitguard.data.UserRepository

class DashboardViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getUser(): LiveData<UserEntity> {
        return userRepository.getUser()
    }

    fun getAllRecords(): LiveData<List<RecordEntity>> {
        return userRepository.getAllRecords()
    }

}