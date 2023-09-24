package com.example.myapplication.adapters


import com.example.myapplication.data.AnalyzedInstruction
import com.example.myapplication.data.ExtendedIngredient
import com.example.myapplication.data.FoodFullInformation


class FullInformationAdapter:Adapter<FoodFullInformation, FoodFullInformation> {
    override fun adapt(t: FoodFullInformation): FoodFullInformation? {
        return if (t.title == null || t.analyzedInstructions == null || t.image == null ||
            t.glutenFree == null || t.dairyFree == null || t.readyInMinutes == null ||
            t.servings == null || t.extendedIngredients == null) {
            null
        } else {
            FoodFullInformation(
                id = t.id,
                title = t.title,
                vegan = t.vegan,
                image = t.image,
                glutenFree = t.glutenFree,
                dairyFree = t.dairyFree,
                readyInMinutes = t.readyInMinutes,
                servings = t.servings,
                extendedIngredients = adaptIngredients(t.extendedIngredients),
                analyzedInstructions = adaptInstructions(t.analyzedInstructions)
            )
        }
    }

    private fun adaptInstructions(instructions: List<AnalyzedInstruction>?): List<AnalyzedInstruction> {
        return instructions ?: emptyList()
    }

    private fun adaptIngredients(ingredients: List<ExtendedIngredient>?): List<ExtendedIngredient> {
        return ingredients ?: emptyList()
    }
}
