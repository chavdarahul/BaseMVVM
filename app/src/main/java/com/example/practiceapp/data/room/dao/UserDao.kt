package com.example.practiceapp.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practiceapp.config.Config
import com.example.practiceapp.data.model.UserData

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserData(data: UserData): Long

    @Query("SELECT * from ${Config.SAVED_DETAILS_TABLE}")
    fun getUserData(): LiveData<List<UserData>>

}