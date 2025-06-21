package com.devforge.fitguard.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devforge.fitguard.data.UserRepository
import com.devforge.fitguard.ui.camera.CameraViewModel
import com.devforge.fitguard.ui.dashboard.DashboardViewModel
import com.devforge.fitguard.ui.result.ResultViewModel
import com.devforge.fitguard.ui.splashscreen.StartViewModel

class UserViewModelFactory(private val repository: UserRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StartViewModel::class.java)) {
            return StartViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CameraViewModel::class.java)) {
            return CameraViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
    companion object {
        @Volatile
        private var INSTANCE: UserViewModelFactory? = null
        fun getInstance(context: Context): UserViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserViewModelFactory(Injection.provideUserRepository(context))
            }.also { INSTANCE = it }

    }
}