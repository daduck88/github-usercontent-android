package com.duck.githubusercontent.data.local.db

import android.content.Context
import androidx.room.*

@Database(
    entities = [UserContentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userContentDao(): UserContentDao

    companion object {
        fun create(appContext: Context) = Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "database_github_user_content"
        ).build()

    }
}