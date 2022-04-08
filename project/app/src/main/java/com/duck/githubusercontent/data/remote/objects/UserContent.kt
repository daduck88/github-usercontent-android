package com.duck.githubusercontent.data.remote.objects

import com.google.gson.annotations.SerializedName

data class UserContent(
    @SerializedName("id") var id: String,
    @SerializedName("first_name") var firstName: String? = null,
    @SerializedName("last_name") var lastName: String? = null,
    @SerializedName("text") var text: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("backgroundColor") var backgroundColor: String? = null,
    @SerializedName("avatar") var avatar: String? = null,
    @SerializedName("avatar_large") var avatarLarge: String? = null
) {
    fun fullName(): String = "$firstName $lastName"
}
