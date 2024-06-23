package com.latihan.githubuser.repository

import com.latihan.githubuser.models.UserModel
import com.latihan.githubuser.services.ApiService
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): UserRepository {
    override suspend fun requestUser(q: String): List<UserModel.Item?> {
        val response = apiService.searchUser(q)
        return response.body()?.items ?: emptyList()
    }
}