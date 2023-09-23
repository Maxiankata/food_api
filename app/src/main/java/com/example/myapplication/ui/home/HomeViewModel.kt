package com.example.myapplication.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.RetrofitFoodApiService
import com.example.myapplication.data.FrontFood
import com.example.myapplication.getApiService
import kotlinx.coroutines.launch

class HomeViewModel(application: Application): AndroidViewModel(application) {


    private var api = application.getApiService()
    private val _recipe = MutableLiveData<FrontFood>()
    private val _trivia = MutableLiveData<String>()
    val trivia: LiveData<String> get() = _trivia
    val recipe: LiveData<FrontFood> get() = _recipe
//    init {
//        fetchRecipe()
//    }
//    private fun fetchRecipe() {
//        viewModelScope.launch {
//            val result = api.getRandomTrivia()
//            _trivia.postValue(result)
//        }
//    }
}