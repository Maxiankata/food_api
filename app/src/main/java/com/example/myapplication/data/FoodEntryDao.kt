package com.example.myapplication.data

import androidx.room.*


@Dao
interface FoodEntryDao {
    @Query("SELECT * FROM foods")
    suspend fun getAllFoods(): List<FoodEntry>

    @Query("SELECT COUNT(id) FROM foods")
    suspend fun countAllFoods(): Int

    @Query("SELECT * FROM foods WHERE id = :id")
    suspend fun getFood(id: Int): FoodEntry

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFoodImages(foodEntry: FoodEntry)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoods(dogEntry: List<FoodEntry>)
}