package com.example.myapplication.api

import FrontFood
import TextPredictor

import kotlinx.coroutines.flow.Flow

interface FoodApiService {
    suspend fun getFoodByComplexSearch(query: String): List<FrontFood>
    suspend fun getRandomRecipe(): Response.FullInformationRecipe
    suspend fun getPrediction(query: String):List<TextPredictor>
    suspend fun getRecipeById(id:Int): Response.FullInformationRecipe
    suspend fun findByNutrients(minCarbs :Int, maxCarbs:Int ,minFat:Int , maxFat:Int , minCalories:Int , maxCalories:Int ,minProtein :Int, maxProtein:Int): List<FrontFood>
    suspend fun findByIngredients(query: String): List<FrontFood>
    suspend fun getRecipeBulk(): Flow<List<Response.FullInformationRecipe>>
    suspend fun getRecipeInstructionsById(id:Int):List<Response.FullInformationRecipe.Steps>
}
