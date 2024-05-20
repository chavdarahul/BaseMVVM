package com.example.practiceapp.data.room.database


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.practiceapp.data.model.UserData
import com.example.practiceapp.data.room.convertor.Converters
import com.example.practiceapp.data.room.dao.UserDao

@Database(
    entities = [UserData::class],
    version = 1,
    exportSchema = false
)
//@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserData(): UserDao
}

