package com.duck.githubusercontent.data.local

import com.duck.githubusercontent.data.UsersContentDataSource
import com.duck.githubusercontent.data.local.db.AppDatabase
import com.duck.githubusercontent.data.local.db.UserContentEntity
import com.duck.githubusercontent.data.remote.objects.UserContent

class UsersContentLocalDataSource(private val appDatabase: AppDatabase) :
    UsersContentDataSource {
    override suspend fun getUsersContent(): List<UserContent> {
        return appDatabase.userContentDao().queryAll().map { userContentEntity ->
            with(userContentEntity) {
                UserContent(
                    id = id,
                    firstName = firstName,
                    lastName = lastName,
                    text = text,
                    email = email,
                    backgroundColor = backgroundColor,
                    avatar = avatar,
                    avatarLarge = avatarLarge
                )
            }
        }
    }

    override suspend fun getUsersContent(pageUrl: String): List<UserContent> {
        throw Exception("DB data source don't implement pagination yet")
    }

    override suspend fun saveUsersContent(usersContent: List<UserContent>) {
        usersContent.map { userContent ->
            with(userContent) {
                UserContentEntity(
                    id = id,
                    firstName = firstName,
                    lastName = lastName,
                    text = text,
                    email = email,
                    backgroundColor = backgroundColor,
                    avatar = avatar,
                    avatarLarge = avatarLarge
                )
            }
        }.let {
            appDatabase.userContentDao().insert(it)
        }
    }
}