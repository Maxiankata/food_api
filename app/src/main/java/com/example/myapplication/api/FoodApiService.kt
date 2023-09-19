package com.example.myapplication.api

import com.example.myapplication.data.FoodItem
import com.example.myapplication.data.FrontFood

interface FoodApiService {
    suspend fun getRecipesByComplexSearch(query: String): List<FrontFood>
    suspend fun getRandomRecipe(): FrontFood?
    val apiService: RetrofitFoodApiService
        get() = RetrofitFoodApiService.getApi()
}