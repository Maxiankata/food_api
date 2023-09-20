package com.example.myapplication.api

import com.example.myapplication.data.FoodItem
import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.TextPredictor

interface FoodApiService {
    suspend fun getFoodByComplexSearch(query: String): List<FrontFood>
    suspend fun getRandomRecipe(): FrontFood?
    suspend fun getPrediction(query: String):List<TextPredictor>
}