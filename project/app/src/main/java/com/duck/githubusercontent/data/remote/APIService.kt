package com.duck.githubusercontent.data.remote

import com.duck.githubusercontent.data.remote.objects.PagesResponse
import com.duck.githubusercontent.data.remote.objects.UserContent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface APIService {

    @GET()
    suspend fun getUsersContent(@Url path: String): List<UserContent>

    @GET("c8ed6c5a9486f311f4725f221b912220/raw/8c6d2d8e1f339d02e0ff3d2990560a4862c4beae/users_page_list")
    suspend fun getPages(): PagesResponse

    companion object {
        internal const val BASE_URL = "https://gist.githubusercontent.com/dsandin/"
        fun create(): APIService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(httpClient)
                .build()
            return retrofit.create(APIService::class.java)

        }
    }
}