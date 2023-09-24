package com.example.myapplication.room

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [FoodRoomInfo::class], version = 1)
abstract class FoodDB : RoomDatabase() {
    abstract fun dao(): FoodDao
}


