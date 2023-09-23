package com.example.myapplication.ui.favorites

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.myapplication.MainActivity
import com.example.myapplication.data.FrontFood
import com.example.myapplication.getApiService
import com.example.myapplication.room.RoomToFullFrontFoodAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
    private val _api = application.getApiService()
    val _foods = MutableLiveData<List<FrontFood>>()
    val foods: LiveData<List<FrontFood>> get() = _foods

    var foodList: List<FrontFood> = emptyList()

    fun getDatabaseEntries(){
        val foodDatabase = MainActivity.getDatabaseInstance()
        val foodBase = foodDatabase.dao()
        var reverseRoomAdapter = RoomToFullFrontFoodAdapter()

        viewModelScope.launch(Dispatchers.IO) {
            val foodRoomInfoList = foodBase.getAllFoods()
            Log.d("FOOD REBASED", foodRoomInfoList.toString())
            val secondlist = foodRoomInfoList.mapNotNull { reverseRoomAdapter.adapt(it) }
            Log.d("FOOD REBASED TWICE", secondlist.toString())

            foodList = secondlist
            Log.d("FOOD REBASED THRICE", foodList.toString())

        }
    }
}