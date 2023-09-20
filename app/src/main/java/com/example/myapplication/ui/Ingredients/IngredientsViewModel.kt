package com.example.myapplication.ui.Ingredients

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.FoodFullInformation
import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.TextPredictor
import com.example.myapplication.getApiService
import kotlinx.coroutines.launch

class IngredientsViewModel(application: Application) : AndroidViewModel(application) {
    private val _api = application.getApiService()
    private val _predictionText = MutableLiveData<List<TextPredictor>>()
    private val _foods = MutableLiveData<List<FrontFood>>()
    private val _ingredients = MutableLiveData<List<FrontFood>>()
    val autoCompleteText: LiveData<List<TextPredictor>> get() = _predictionText
    private val _recipe = MutableLiveData<FoodFullInformation?>()
    val recipe: MutableLiveData<FoodFullInformation?> get() = _recipe

    val recipes: LiveData<List<FrontFood>> get() = _foods
    companion object {
        private const val NUMBER_OF_INGREDIENTS: Int = 6
    }
//    init {
//        fetchRecipe()
//    }
//
//    private fun fetchRecipe() {
//        viewModelScope.launch {
//            val result =
//                _api.getRandomRecipe()
//            _recipe.postValue(result)
//        }
//    }
    fun fetchPredictionText(query: String){
        viewModelScope.launch {
            _predictionText.postValue(_api.getPrediction(query))
        }
    }

    fun fetchFood(query: String){
        viewModelScope.launch {
            _foods.postValue(_api.getFoodByComplexSearch(query))
        }
    }
}