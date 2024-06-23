package com.latihan.githubuser.services

import com.latihan.githubuser.models.UserModel
import com.latihan.githubuser.utils.ApiConstant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(ApiConstant.SEARCH_USER)
    suspend fun searchUser(@Query("q") q: String): Response<UserModel>
}