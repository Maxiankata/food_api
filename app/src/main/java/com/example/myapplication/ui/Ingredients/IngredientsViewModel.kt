package com.example.myapplication.ui.Ingredients

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.FrontFood

class IngredientsViewModel(application: Application) : AndroidViewModel(application) {

    private val _ingredients = MutableLiveData<List<FrontFood>>()

    companion object {
        private const val NUMBER_OF_INGREDIENTS: Int = 6
    }
    init {
        loadIngredients()
    }
    private fun ingredientBuilder(ingredientName: String, id:Int) =
        FrontFood(
            id = id,
            title = ingredientName,
            imageUrl = String.toString()
        )
    fun loadIngredients(){
        val ingredientsList:MutableList<FrontFood> = mutableListOf()
        val ingredientsTitle = "Food Title"

    }
}