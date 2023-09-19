package com.example.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Foods")
data class FoodEntry(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name:String,

    @ColumnInfo(name = "ingredients") val ingredients:List<String>,
    @ColumnInfo(name = "calories") val calories:Int,
    @ColumnInfo(name = "proteins") val proteins:Int,
    @ColumnInfo(name = "carbs") val carbs:Int,
    @ColumnInfo(name = "fat") val fat:Int,
    @ColumnInfo(name = "image") val image:String,


    )
