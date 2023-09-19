package com.example.myapplication.data

data class FoodItem(
    val name: String = "",
    val ingredients: List<String>,
    val calories: Int,
    val carbs: Int,
    val fats: Int,
    val protein: Int,
    val id: Int,
    val image: String
)
