package com.devforge.fitguard.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devforge.fitguard.data.RecordEntity
import com.devforge.fitguard.data.UserRepository

class HistoryViewModel(private val repository: UserRepository) : ViewModel() {
    fun getAllRecords(): LiveData<List<RecordEntity>> = repository.getAllRecords()
}