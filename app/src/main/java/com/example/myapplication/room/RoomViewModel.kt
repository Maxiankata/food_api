package com.example.myapplication.room

import androidx.lifecycle.ViewModel
import androidx.room.Room

class RoomViewModel(private val dao: FoodDao):ViewModel() {
//    val db = Room.databaseBuilder(
//        applicationContext,
//        FoodDB::class.java, "food-base"
//    ).build()
}