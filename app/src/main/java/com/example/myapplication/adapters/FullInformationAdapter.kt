package com.example.myapplication.adapters

//import com.example.myapplication.data.ApiIngredients
//import com.example.myapplication.data.ApiSteps
//import com.example.myapplication.data.ApiResponse
import com.example.myapplication.data.FoodFullInformation
//import com.example.myapplication.data.Ingredients
//import com.example.myapplication.data.Instructions
import com.example.myapplication.data.Response


class FullInformationAdapter:Adapter<FoodFullInformation, Response.FullInformationRecipe> {
    override fun adapt(t: FoodFullInformation): Response.FullInformationRecipe? {
        return if (t.title == null ||t.vegan==null ||t.instructions == null || t.image == null || t.glutenFree == null || t.dairyFree == null || t.readyInMinutes == null || t.servings == null  || t.ingredients == null) {
            null
        } else {
            return Response.FullInformationRecipe(
                id = t.id,
                title = t.title,
                vegan = t.vegan,
                image = t.image,
                glutenFree = t.glutenFree,
                dairyFree = t.dairyFree,
                readyInMinutes = t.readyInMinutes,
                servings = t.servings,
                ingredients = adaptIngredients(t.ingredients),
                analyzedInstructions = adaptInstructions(t.instructions)
            )
        }
    }

    private fun adaptInstructions(instructions: List<FoodFullInformation.ApiInstructions.ApiSteps>?): List<Response.FullInformationRecipe.Instructions.Steps> {
        return instructions?.map {
            Response.FullInformationRecipe.Instructions.Steps(
                number = it.number ?: 0,
                step = it.instruction ?: ""
            )
        } ?: emptyList()
    }

    private fun adaptIngredients(ingredients: List<FoodFullInformation.ApiIngredients>?): List<Response.FullInformationRecipe.Ingredients> {
        return ingredients?.map {
            Response.FullInformationRecipe.Ingredients(
                name = it.name
            )
        } ?: emptyList()
    }
}
