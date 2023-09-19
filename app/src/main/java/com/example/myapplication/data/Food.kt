package com.example.myapplication.data


@kotlinx.serialization.Serializable
data class Food(
    val id: Int,
    val title: String? = null,
    val image: String? = null,
    val imageType:String

)
data class RecipeResponse(
    val results: List<Food?> = emptyList(),
    val offset: Int? = 0,
    val number: Int? = 0,
    val totalResults: Int? = 0
)
