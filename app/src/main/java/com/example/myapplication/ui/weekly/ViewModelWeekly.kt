package com.example.myapplication.ui.weekly

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.Response.FullInformationRecipe
import com.example.myapplication.getApiService
import kotlinx.coroutines.launch

class ViewModelWeekly(application: Application) : AndroidViewModel(application) {
    private val _api = application.getApiService()
    val _foods = MutableLiveData<List<FrontFood>>()
    private val _recipe = MutableLiveData<FullInformationRecipe>()
    val recipe: MutableLiveData<FullInformationRecipe> get() = _recipe

        init {
        fetchRecipe()
    }

    private fun fetchRecipe() {
        viewModelScope.launch {
            val result =
                _api.getFoodByComplexSearch(query = "pasta")
            _foods.postValue(result)
        }
    }
}