package com.example.myapplication.ui.Ingredients

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.RandomResponse.Null.FoodFullInformation
import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.TextPredictor
import com.example.myapplication.getApiService
import kotlinx.coroutines.launch

class IngredientsViewModel(application: Application) : AndroidViewModel(application) {
    private val _api = application.getApiService()
    private val _predictionText = MutableLiveData<List<TextPredictor>>()
    private val _foods = MutableLiveData<List<FrontFood>>()
    val autoCompleteText: LiveData<List<TextPredictor>> get() = _predictionText
    private val _recipe = MutableLiveData<FoodFullInformation?>()
    val recipe: MutableLiveData<FoodFullInformation?> get() = _recipe

    val recipes: LiveData<List<FrontFood>> get() = _foods

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