package com.example.myapplication.adapters

import com.example.myapplication.data.ApiIngredients
import com.example.myapplication.data.ApiInstructions
//import com.example.myapplication.data.ApiResponse
import com.example.myapplication.data.FoodFullInformation
import com.example.myapplication.data.Response.Steps
import com.example.myapplication.data.Response.Ingredients

import com.example.myapplication.data.Response.FullInformationRecipe

class FullInformationAdapter:Adapter<FoodFullInformation, FullInformationRecipe> {
    override fun adapt(t: FoodFullInformation): FullInformationRecipe? {
        return if (t.title == null ||t.vegan==null || t.image == null || t.glutenFree == null || t.dairyFree == null || t.readyInMinutes == null || t.servings == null || t.instructions == null || t.ingredients == null) {
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
                analyzedInstructions = adaptInstructions(t.instructions),
                ingredients = adaptIngredients(t.ingredients),
            )
        }
    }

    private fun adaptInstructions(instructions: List<ApiInstructions>?): List<Steps> {
        return instructions?.map {
            Steps(
                number = it.number ?: 0,
                step = it.step ?: ""
            )
        } ?: emptyList()
    }

    private fun adaptIngredients(ingredients: List<ApiIngredients>?): List<Ingredients> {
        return ingredients?.map {
            Ingredients(
                name = it.name
            )
        } ?: emptyList()
    }
}