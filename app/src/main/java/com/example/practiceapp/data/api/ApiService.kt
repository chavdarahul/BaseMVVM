package com.example.practiceapp.data.api

import com.example.practiceapp.data.model.GetUserListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
   suspend fun getUsers(
        @Query("page") page: Int,
    ): GetUserListResponse
}