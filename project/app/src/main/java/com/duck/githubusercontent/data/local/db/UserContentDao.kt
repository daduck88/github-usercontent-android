package com.duck.githubusercontent.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserContentDao {
    @Query("select * from user_content ")
    suspend fun queryAll(): List<UserContentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userContents: List<UserContentEntity>)

}