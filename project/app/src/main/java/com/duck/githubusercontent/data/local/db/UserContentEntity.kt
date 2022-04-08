package com.duck.githubusercontent.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_content")
data class UserContentEntity(
    @PrimaryKey
    var id: String,
    var firstName: String? = null,
    var lastName: String? = null,
    var text: String? = null,
    var email: String? = null,
    var backgroundColor: String? = null,
    var avatar: String? = null,
    var avatarLarge: String? = null
)