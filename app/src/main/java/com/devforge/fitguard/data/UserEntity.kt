package com.devforge.fitguard.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "userId")
    val userId: Int,
    @ColumnInfo(name = "name")
     val name: String? = null,
    @ColumnInfo(name = "age")
     val birthDate: Int = 0,
    @ColumnInfo(name = "gender")
     val gender: String? = null,
    @ColumnInfo(name = "bodyWeight")
     val bodyWeight: Int = 0,
    @ColumnInfo(name = "bodyHeight")
     val bodyHeight: Int = 0,
    )
