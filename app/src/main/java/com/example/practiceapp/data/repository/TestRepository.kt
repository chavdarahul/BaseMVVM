package com.example.practiceapp.data.repository

import com.example.practiceapp.data.room.dao.UserDao
import com.example.practiceapp.data.api.ApiService
import com.example.practiceapp.data.api.SafeApiCall
import com.example.practiceapp.data.model.UserData
import javax.inject.Inject

class TestRepository @Inject constructor(
    private var api: ApiService,
    private val dao: UserDao
) : SafeApiCall {

    suspend fun getUsers(page: Int) = safeApiCall {
        api.getUsers(page)
    }

    suspend fun insertUserData(data: UserData) = dao.insertUserData(data)

    fun getUserData() = dao.getUserData()

}