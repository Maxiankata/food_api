package com.example.myapplication.api

import com.example.myapplication.data.FoodItem

interface FoodApiService {
    suspend fun getItems(): List<FoodItem>
}