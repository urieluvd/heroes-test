package com.ezamora.test.views.models

import com.google.gson.annotations.SerializedName

data class HeroesModel(
    @SerializedName("code") val code: Int,
    @SerializedName("data") var data: Data
)

data class Data(
    @SerializedName("limit") val limit: Int,
    @SerializedName("results") val results: List<Results>
)

data class Results(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") var description: String,
    @SerializedName("thumbnail") val thumbnail: Thumbnail,
)

data class Thumbnail(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String,
)
