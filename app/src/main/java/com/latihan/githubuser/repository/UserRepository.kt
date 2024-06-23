package com.latihan.githubuser.repository

import com.latihan.githubuser.models.UserModel
import retrofit2.Response

interface UserRepository {
    suspend fun requestUser(q: String): List<UserModel.Item?>
}