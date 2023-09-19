package com.example.myapplication.data

import com.google.gson.annotations.SerializedName


@kotlinx.serialization.Serializable

data class Food(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("imageType") val imageType: String? = null
)
data class RecipeResponse(
    @SerializedName("results") val results: List<Food?> = emptyList(),
    @SerializedName("offset") val offset: Int? = 0,
    @SerializedName("number") val number: Int? = 0,
    @SerializedName("totalResults") val totalResults: Int? = 0
)
