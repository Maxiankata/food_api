package com.example.myapplication.ui.extendedInformation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Response.FullInformationRecipe
import com.example.myapplication.getApiService
import com.example.myapplication.getStorage
import kotlinx.coroutines.launch

class ExtendedInformationViewModel(application: Application) : AndroidViewModel(application) {
    private val _api = application.getApiService()
    private val _foods = MutableLiveData<FullInformationRecipe>()

    private val storage = application.getStorage()
    fun addExtendedFoodId(id: Int){ storage.addFoodId(id) }

    val foods: LiveData<FullInformationRecipe> get() = _foods
    fun fetchFood(id: Int){
        viewModelScope.launch {
            _foods.postValue(_api.getRecipeById(id))
            Log.d("FETCH", "FOOD FETCHED")
        }
    }

}