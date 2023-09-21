package com.example.myapplication.data


data class FullInformationRecipe(
val id: Int,
val title: String? = null,
val image: String? = null,
val readyInMinutes:Int? = null,
val dairyFree:Boolean? = null,
val glutenFree:Boolean? = null,
val vegan:Boolean? = null,
val servings:Boolean? = null,
val analyzedInstructions:List<Steps>?=null,
val ingredients:List<Ingredients>?=null
)
{
    data class Ingredients(
        val name:Int? = null,
    )
    data class Steps(
        val number:Int? = null,
        val step: String? = null
    )
}

