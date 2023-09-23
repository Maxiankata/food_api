package com.example.myapplication.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Upsert
    fun saveFood(foodRoomInfo: FoodRoomInfo)
    @Delete
    fun deleteFood(foodRoomInfo: FoodRoomInfo)
    @Query("SELECT * FROM foodroominfo")
    fun getAllFoods(): List<FoodRoomInfo>

    @Query("SELECT * FROM foodroominfo ORDER BY name ASC")
    fun orderFoodByNameAsc(): LiveData<List<FoodRoomInfo>>
    @Query("SELECT * FROM foodroominfo ORDER BY name DESC")
    fun orderFoodByNameDesc(): LiveData<List<FoodRoomInfo>>
}

data class DaoFunctions(
    val foods:List<FoodRoomInfo> = emptyList(),

    )