package com.example.myapplication.data

import com.google.gson.JsonObject

data class FoodItem(
    val id: Int,
    val name: String = "",
    val ingredients: List<String>,
    val calories: Int,
    val carbs: Int,
    val fats: Int,
    val protein: Int,
    val image: String
)
