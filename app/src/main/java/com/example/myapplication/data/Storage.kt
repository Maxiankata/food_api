package com.example.myapplication.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Storage(application: Application) {

    companion object{
        const val PERSISTANCE_STORE_NAME = "unsureNeed"

        const val PERSISTANCE_STORE_KEY_FOOD_IDS = "foodIds"

    }
    private val sharedPreferences = application.getSharedPreferences(PERSISTANCE_STORE_NAME, Context.MODE_PRIVATE)
    private val _foodEntries = MutableLiveData(getFoodIds())
    val foodEntries: LiveData<List<Int>> = _foodEntries

    fun addFoodId(id:Int) {
        val newValue = getFoodIds()
            .toMutableSet()
            .apply { add(id) }
            .joinToString(separator = ",")

        sharedPreferences.edit().putString(PERSISTANCE_STORE_KEY_FOOD_IDS, newValue).apply()
        _foodEntries.value = getFoodIds()
    }
    fun getFoodIds(): List<Int> {
        return sharedPreferences.getString(PERSISTANCE_STORE_KEY_FOOD_IDS, null)
            ?.split(",")
            ?.map { Integer.parseInt(it) }
            ?: emptyList()
    }
    fun getNumberOfFavoriteFoods() = getFoodIds().size

}