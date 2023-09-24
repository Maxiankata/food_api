package com.example.myapplication.ui.extendedInformation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.FoodFullInformation
import com.example.myapplication.getApiService
import kotlinx.coroutines.launch

class ExtendedInformationViewModel(application: Application) : AndroidViewModel(application) {
    private val _api = application.getApiService()
    private val _foods = MutableLiveData<FoodFullInformation>()
    val foods: LiveData<FoodFullInformation> get() = _foods
    fun fetchFood(id: Int){
        viewModelScope.launch {
            _foods.postValue(_api.getRecipeById(id))
        }
    }

}