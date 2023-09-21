package com.example.myapplication.data

import com.google.gson.annotations.SerializedName
@kotlinx.serialization.Serializable

data class RandomResponse(
    @SerializedName("recipes") val randomResponse: Array<Null.FoodFullInformation>
){
    data class Null(
        @SerializedName("0") val id: List<FoodFullInformation>,
    ){


        data class FoodFullInformation(
            @SerializedName("id") val id: Int,
            @SerializedName("title") val title: String? = null,
            @SerializedName("image") val image: String? = null,
            @SerializedName("readyInMinutes") val readyInMinutes: Int? = null,
            @SerializedName("dairyFree") val dairyFree: Boolean? = null,
            @SerializedName("glutenFree") val glutenFree: Boolean? = null,
            @SerializedName("vegan") val vegan: Boolean? = null,
            @SerializedName("servings") val servings: Boolean? = null,
            @SerializedName("analyzedInstructions") val analyzedInstructions: List<Steps>? = null,
            @SerializedName("extendedIngredients") val ingredients: List<Ingredients>? = null
        )

        data class Ingredients(
            @SerializedName("original") val name: Int? = null,
        )

        data class Steps(
            @SerializedName("number") val number: Int? = null,
            @SerializedName("step") val step: String? = null
        )
    }
}
