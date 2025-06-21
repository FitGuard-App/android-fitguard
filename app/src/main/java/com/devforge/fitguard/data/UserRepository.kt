package com.devforge.fitguard.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(userEntity: UserEntity) {
        userDao.insertUser(userEntity)
    }

    fun getUser(): LiveData<UserEntity> {
        return userDao.getUser()
    }

    suspend fun updateUser(userEntity: UserEntity) {
        userDao.updateUser(userEntity)
    }

    suspend fun insertRecord(recordEntity: RecordEntity) {
        userDao.insertRecord(recordEntity)
    }

    fun getAllRecords(): LiveData<List<RecordEntity>> {
        return userDao.getAllRecords()
    }


    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null
        fun getInstance(
            userDao: UserDao
        ): UserRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserRepository(userDao)
            }.also { INSTANCE = it }
    }
}