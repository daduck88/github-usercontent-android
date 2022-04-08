package com.duck.githubusercontent.data.remote

import com.duck.githubusercontent.data.remote.objects.PagesResponse
import javax.inject.Inject

class PagesDataSourceImp @Inject constructor(private val apiService: APIService) :
    PagesDataSource {
    override suspend fun getPages() = apiService.getPages()

}

interface PagesDataSource {
    suspend fun getPages(): PagesResponse
}