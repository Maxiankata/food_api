package com.example.myapplication.api

import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.Response.FullInformationRecipe
import com.example.myapplication.data.TextPredictor

interface FoodApiService {
    suspend fun getFoodByComplexSearch(query: String): List<FrontFood>
    suspend fun getRandomRecipe(): FullInformationRecipe
    suspend fun getPrediction(query: String):List<TextPredictor>
    suspend fun getRecipeById(id:Int): FullInformationRecipe
}