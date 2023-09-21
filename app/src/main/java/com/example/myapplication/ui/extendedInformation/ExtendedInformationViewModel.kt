package com.example.myapplication.ui.extendedInformation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.FullInformationRecipe
import com.example.myapplication.getApiService
import kotlinx.coroutines.launch

class ExtendedInformationViewModel(application: Application) : AndroidViewModel(application) {
    private val _api = application.getApiService()
    private val _foods = MutableLiveData<FullInformationRecipe>()
    val foods: LiveData<FullInformationRecipe> get() = _foods
//    fun fetchFood(query: String){
//        viewModelScope.launch {
//            foods.postValue(_api.getRandomRecipe())
//        }
//    }
}