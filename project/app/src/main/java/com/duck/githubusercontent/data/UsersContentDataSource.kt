package com.duck.githubusercontent.data

import com.duck.githubusercontent.data.remote.objects.UserContent

interface UsersContentDataSource {
    suspend fun getUsersContent() : List<UserContent>
    suspend fun getUsersContent(pageUrl: String) : List<UserContent>
    suspend fun saveUsersContent(usersContent: List<UserContent>)
}