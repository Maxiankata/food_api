package com.example.myapplication.data

data class FoodImageSearch(
    val name: List<Food> = emptyList(),
    val id: String? = null,
    val url: String? = null

)
