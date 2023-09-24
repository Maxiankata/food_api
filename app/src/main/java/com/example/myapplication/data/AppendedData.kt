package com.example.myapplication.data

@kotlinx.serialization.Serializable

data class FrontFood(
    val id: Int,
    val title: String,
    val image: String,
)
data class ExtendedIngredient(
    val original: String,
)
data class AnalyzedInstruction(
    val name: String?,
    val steps: List<Step>?
)

data class Step(
    val number: Int,
    val step: String
)

data class FoodFullInformation(
    val vegan: Boolean,
    val glutenFree: Boolean,
    val dairyFree: Boolean,
    val servings: Int,
    val extendedIngredients: List<ExtendedIngredient>,
    val id: Int,
    val title: String,
    val readyInMinutes: Int,
    val image: String,
    val analyzedInstructions: List<AnalyzedInstruction>
)

data class RecipeResponse(
    val recipes: List<FoodFullInformation>
)
data class TextPredictor(
    var id: Int,
    var name: String
)
