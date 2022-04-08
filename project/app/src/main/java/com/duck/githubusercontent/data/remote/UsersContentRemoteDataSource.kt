package com.duck.githubusercontent.data.remote

import com.duck.githubusercontent.data.UsersContentDataSource
import com.duck.githubusercontent.data.remote.objects.UserContent
import javax.inject.Inject

class UsersContentRemoteDataSource @Inject constructor(private val apiService: APIService) :
    UsersContentDataSource {
    override suspend fun getUsersContent(): List<UserContent> {
        throw Exception("API data source needs pagination to get UsersContent")
    }

    override suspend fun getUsersContent(pageUrl: String): List<UserContent> {
        var path = pageUrl.removePrefix(APIService.BASE_URL)
        return apiService.getUsersContent(path).filter { it.avatarLarge != null }
    }

    override suspend fun saveUsersContent(usersContent: List<UserContent>) {
        throw Exception("API data source doesn't insert Data")
    }
}