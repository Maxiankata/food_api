package com.example.myapplication.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Response.FullInformationRecipe
import com.example.myapplication.getApiService
import com.example.myapplication.getStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application): AndroidViewModel(application) {
    private val storage = application.getStorage()
    private val apiService = application.getApiService()

    private val _foodInformation = MutableLiveData<FullInformationRecipe?>()
    val foodInformation: LiveData<FullInformationRecipe?> = _foodInformation

    private val _foodEntries = MutableLiveData<List<FullInformationRecipe>>()
    val foodEntries: LiveData<List<FullInformationRecipe>> = _foodEntries

    fun refreshDogEntries() {
        viewModelScope.launch(Dispatchers.IO) {
            storage.foodEntries.value?.let { ids ->

            }
        }
    }
}