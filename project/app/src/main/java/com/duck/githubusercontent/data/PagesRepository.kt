package com.duck.githubusercontent.data

import com.duck.githubusercontent.data.remote.PagesDataSource

class PagesRepositoryImp(
    private val dataSource: PagesDataSource
) : PagesRepository {

    override suspend fun getPages(): List<String> {
        return dataSource.getPages().pages
    }
}

interface PagesRepository {
    suspend fun getPages(): List<String>
}