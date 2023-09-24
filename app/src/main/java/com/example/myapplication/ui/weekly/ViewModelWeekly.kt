package com.example.myapplication.ui.weekly

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.FrontFood
//import com.example.myapplication.data.Response.FullInformationRecipe
import com.example.myapplication.getApiService
import kotlinx.coroutines.launch

class ViewModelWeekly(application: Application) : AndroidViewModel(application) {
    private val _api = application.getApiService()
    val _foods = MutableLiveData<List<FrontFood>>()
    private val _recipe = MutableLiveData<List<FrontFood>>()
    val recipe: MutableLiveData<List<FrontFood>> get() = _recipe

        init {
        fetchRecipe()
    }

    private fun fetchRecipe() {
        viewModelScope.launch {
            val result = _api.getRandomRecipe()
            _foods.postValue(result)
        }
    }
}