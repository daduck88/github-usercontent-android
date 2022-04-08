package com.duck.githubusercontent.di

import android.content.Context
import com.duck.githubusercontent.data.*
import com.duck.githubusercontent.data.local.UsersContentLocalDataSource
import com.duck.githubusercontent.data.local.db.AppDatabase
import com.duck.githubusercontent.data.remote.APIService
import com.duck.githubusercontent.data.remote.PagesDataSourceImp
import com.duck.githubusercontent.data.remote.UsersContentRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {

    @Singleton
    @Provides
    fun provideAPIService() = APIService.create()

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext appContext: Context) = AppDatabase.create(appContext)


    @Provides
    fun providePagesRepository(dataSource: PagesDataSourceImp): PagesRepository {
        return PagesRepositoryImp(dataSource)
    }

    @Provides
    fun provideUsersContentRepository(
        @Named("remoteUsersContentDatasource")
        remoteDataSource: UsersContentDataSource,
        @Named("localUsersContentDatasource")
        localDataSource: UsersContentDataSource,
    ): UsersContentRepository {
        return UsersContentRepositoryImp(remoteDataSource, localDataSource)
    }

    @Provides
    @Named("remoteUsersContentDatasource")
    fun provideRemoteUsersContentDataSource(
        apiService: APIService
    ): UsersContentDataSource {
        return UsersContentRemoteDataSource(apiService)
    }

    @Provides
    @Named("localUsersContentDatasource")
    fun provideLocalUsersContentDataSource(
        appDatabase: AppDatabase
    ): UsersContentDataSource {
        return UsersContentLocalDataSource(appDatabase)
    }
}
