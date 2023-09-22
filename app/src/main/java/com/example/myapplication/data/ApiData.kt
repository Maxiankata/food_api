package com.example.myapplication.data

import com.google.gson.annotations.SerializedName
@kotlinx.serialization.Serializable
//data class ApiResponse(
//    @SerializedName("recipes") val randomResponse: List<FoodFullInformation>
//){
    data class FoodFullInformation(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String? = null,
        @SerializedName("image") val image: String? = null,
        @SerializedName("readyInMinutes") val readyInMinutes: Int? = null,
        @SerializedName("dairyFree") val dairyFree: Boolean? = null,
        @SerializedName("glutenFree") val glutenFree: Boolean? = null,
        @SerializedName("vegan") val vegan: Boolean? = null,
        @SerializedName("servings") val servings: String? = null,
        @SerializedName("analyzedInstructions") val instructions: List<ApiInstructions>? = null,
        @SerializedName("extendedIngredients") val ingredients: List<ApiIngredients>? = null
    )
    data class ApiIngredients(
        @SerializedName("original") val name: String? = null,
    )

    data class ApiInstructions(
        @SerializedName("number") val number: Int? = null,
        @SerializedName("step") val step: String? = null
    )
//}
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
@kotlinx.serialization.Serializable
data class TextPredictorJsonStealer(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String? = null,
)