package com.devforge.fitguard.ui.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devforge.fitguard.data.UserEntity
import com.devforge.fitguard.data.UserRepository
import kotlinx.coroutines.launch

class ResultViewModel(private val userRepository: UserRepository) : ViewModel(){
    fun insert(record: RecordEntity) {
        viewModelScope.launch {
            userRepository.insertRecord(record)
        }
    }
}