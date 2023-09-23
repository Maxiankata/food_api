package com.example.myapplication.api

import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.ApiRandomTrivia
import com.example.myapplication.data.Response.FullInformationRecipe
//import com.example.myapplication.data.Steps
import com.example.myapplication.data.TextPredictor
import kotlinx.coroutines.flow.Flow

interface FoodApiService {
    suspend fun getFoodByComplexSearch(query: String): List<FrontFood>
    suspend fun getRandomRecipe(): List<FrontFood>
    suspend fun getPrediction(query: String):List<TextPredictor>
    suspend fun getRecipeById(id:Int): FullInformationRecipe
    suspend fun findByNutrients(minCarbs :Int, maxCarbs:Int ,minFat:Int , maxFat:Int , minCalories:Int , maxCalories:Int ,minProtein :Int, maxProtein:Int): List<FrontFood>
    suspend fun findByIngredients(query: String): List<FrontFood>
    suspend fun getRecipeInstructionsById(id:Int):List<FullInformationRecipe.Instructions.Steps>
    suspend fun getRandomTrivia(): String

}
