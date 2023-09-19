//package com.example.myapplication.data
//
//import com.example.myapplication.Adapter
//
//class FoodToFoodEntryAdapter: Adapter<Food, FoodEntry> {
//    fun adapt(t: FoodItem): FoodEntry? {
//        return t.id.let {
//            FoodEntry(
//                it,
//                t.name,
//                t.ingredients,
//                t.carbs,
//                t.fats,
//                t.calories,
//                t.protein,
//                t.image
//            )
//        }
//    }
//}