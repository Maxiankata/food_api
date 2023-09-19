package com.example.myapplication.data

data class FoodImageSearch(
    val id: Int? = null,
    val name: List<Food> = emptyList(),
    val url: String? = null

)
