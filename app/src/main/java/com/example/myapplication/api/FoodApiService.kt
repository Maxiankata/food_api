package com.example.myapplication.api

import com.example.myapplication.data.FoodFullInformation
import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.Step
import com.example.myapplication.data.TextPredictor

interface FoodApiService {
    suspend fun getFoodByComplexSearch(query: String): List<FrontFood>
    suspend fun getRandomRecipe(): List<FrontFood>
    suspend fun getPrediction(query: String):List<TextPredictor>
    suspend fun getRecipeById(id:Int): FoodFullInformation
    suspend fun findByNutrients(minCarbs :Int, maxCarbs:Int ,minFat:Int , maxFat:Int , minCalories:Int , maxCalories:Int ,minProtein :Int, maxProtein:Int): List<FrontFood>
    suspend fun findByIngredients(query: String): List<FrontFood>
    suspend fun getRecipeInstructionsById(id:Int):List<Step>?
    suspend fun getRandomTrivia(): String

}
