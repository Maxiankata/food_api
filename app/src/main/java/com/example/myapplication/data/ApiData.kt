package com.example.myapplication.data

import com.google.gson.annotations.SerializedName
@kotlinx.serialization.Serializable
data class ApiRandomTrivia(
    @SerializedName("text") val text:String? = null
)

data class Food(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String? = null,
    @SerializedName("image") val image: String? = null,
)

data class IngredientResponse(
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title: String? = null,
    @SerializedName("image") val image: String? = null
)
data class NutritionRequest(
    @SerializedName("id") val id:Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("minCarbs") val minCarbs:Int?,
    @SerializedName("maxCarbs") val maxCarbs:Int?,
    @SerializedName("minProtein") val minProtein:Int?,
    @SerializedName("maxProtein") val maxProtein:Int?,
    @SerializedName("minCalories") val minCalories:Int?,
    @SerializedName("maxCalories") val maxCalories:Int?,
    @SerializedName("minFat") val minFat:Int?,
    @SerializedName("maxFat") val maxFat:Int?
)

data class FoodResponse(
    @SerializedName("results") val results: List<Food?> = emptyList(),
    @SerializedName("offset") val offset: Int? = 0,
    @SerializedName("number") val number: Int? = 0,
    @SerializedName("totalResults") val totalResults: Int? = 0
)
data class RandomResponse(
    @SerializedName("recipes") val results: List<Food?> = emptyList(),

)

@kotlinx.serialization.Serializable
data class TextPredictorJsonStealer(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String? = null,
)
