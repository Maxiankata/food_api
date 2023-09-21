package com.example.myapplication.adapters

import com.example.myapplication.data.FullInformationRecipe
import com.example.myapplication.data.RandomResponse

class FullInformationAdapter:Adapter<RandomResponse.Null.FoodFullInformation, FullInformationRecipe> {
    override fun adapt(t: RandomResponse.Null.FoodFullInformation): FullInformationRecipe? {
        return if (t.title == null ||t.vegan==null || t.image == null || t.glutenFree == null || t.dairyFree == null || t.readyInMinutes == null || t.servings == null || t.analyzedInstructions == null || t.ingredients == null) {
            null
        } else {
            return FullInformationRecipe(
                id = t.id,
                title = t.title,
                vegan = t.vegan,
                image = t.image,
                glutenFree = t.glutenFree,
                dairyFree = t.dairyFree,
                readyInMinutes = t.readyInMinutes,
                servings = t.servings,
                analyzedInstructions = adaptInstructions(t.analyzedInstructions),
                ingredients = adaptIngredients(t.ingredients),

            )
        }

    }

    fun adaptInstructions(instructions: List<RandomResponse.Null.Steps>?): List<FullInformationRecipe.Steps> {
        return instructions?.map {
            FullInformationRecipe.Steps(
                number = it.number ?: 0,
                step = it.step ?: ""
            )
        } ?: emptyList()
    }

    fun adaptIngredients(ingredients: List<RandomResponse.Null.Ingredients>?): List<FullInformationRecipe.Ingredients> {
        return ingredients?.map {
            FullInformationRecipe.Ingredients(
                name = it.name
            )
        } ?: emptyList()
    }
}