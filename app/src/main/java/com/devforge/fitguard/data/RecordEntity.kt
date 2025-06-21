package com.devforge.fitguard.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "record")
data class RecordEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recordId")
    val id: Int = 0,
    @ColumnInfo(name = "calorie")
    val calorie: Int = 0,
    @ColumnInfo(name = "repetition")
    val repetition: Int = 0,
    @ColumnInfo(name = "totalDuration")
    val totalDuration: Int = 0,
    @ColumnInfo(name = "exercise_count")
    val exerciseCount: Int = 0,
    @ColumnInfo(name = "date")
    val date: String? = null
)
