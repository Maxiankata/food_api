package com.example.myapplication.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.RetrofitFoodApiService
import com.example.myapplication.data.FrontFood
import com.example.myapplication.getApiService
import kotlinx.coroutines.launch

//class HomeViewModel : ViewModel() {
//
//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text
//}
class HomeViewModel(application: Application): AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    private var api: RetrofitFoodApiService = application.getApiService()
    private val _recipe = MutableLiveData<FrontFood>()
    val recipe: LiveData<FrontFood> get() = _recipe
    init {
        fetchRecipe()
    }

    private fun fetchRecipe() {
        viewModelScope.launch {
//            val result =
//            listOf(api.getRecipesByComplexSearch("cookies").last(),
//                api.getRecipesByComplexSearch("lobster").last(),
//                api.getRecipesByComplexSearch("watermelon").last(),
//                api.getRecipesByComplexSearch("cola").last()).random()
//            _recipe.postValue(result)
        }
    }


    val text: LiveData<String> = _text
}