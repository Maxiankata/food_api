package com.example.myapplication.data

data class FoodItem(
    val id: Int,
    val name: String = "",
    val ingredients: List<String>,
    val calories: Int,
    val carbs: Int,
    val fats: Int,
    val protein: Int,
    val image: String
)


data class FrontFood(
    val id: Int,
    val title: String,
    val image: String,
)

data class Response(
    var response: List<FullInformationRecipe>
) {
    data class FullInformationRecipe(
        val id: Int,
        val title: String? = null,
        val image: String? = null,
        val readyInMinutes: Int? = null,
        val dairyFree: Boolean? = null,
        val glutenFree: Boolean? = null,
        val vegan: Boolean? = null,
        val servings: String? = null,
        val ingredients: List<Ingredients>? = emptyList(),
        val analyzedInstructions: List<Instructions.Steps> = emptyList()
    ){
        data class Ingredients(
            val name: String? = null,
        )
        data class Instructions(
            val name:String?=null,
            val steps: List<Steps>
        ){
            data class Steps(
                val number: Int,
                val step: String
            )
        }
    }
}




data class TextPredictor(
    var id: Int,
    var name: String
)


data class FoodImageSearch(
    val id: Int? = null,
    val name: List<Food> = emptyList(),
    val url: String? = null
)
