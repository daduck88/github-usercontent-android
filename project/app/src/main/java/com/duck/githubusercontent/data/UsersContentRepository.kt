package com.duck.githubusercontent.data

import com.duck.githubusercontent.data.remote.objects.UserContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException

class UsersContentRepositoryImp(
    private val remoteDataSource: UsersContentDataSource,
    private val localDataSource: UsersContentDataSource,
) : UsersContentRepository {
    override suspend fun getUsersContent(paginationURL: String): Flow<List<UserContent>> = flow {
        try{
            val remoteResponse = remoteDataSource.getUsersContent(paginationURL)
            emit(remoteResponse)
            localDataSource.saveUsersContent(remoteResponse)
        } catch (e : Exception){
            emit(localDataSource.getUsersContent())
        }
    }.flowOn(Dispatchers.IO)
}

interface UsersContentRepository {
    suspend fun getUsersContent(paginationURL: String): Flow<List<UserContent>>
}