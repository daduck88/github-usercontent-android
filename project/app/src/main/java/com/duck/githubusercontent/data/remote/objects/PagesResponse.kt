package com.duck.githubusercontent.data.remote.objects

import com.google.gson.annotations.SerializedName

data class PagesResponse(
    @SerializedName("pages")
    val pages: List<String>
)
