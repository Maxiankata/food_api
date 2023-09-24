package com.example.myapplication.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.MainActivity
import com.example.myapplication.data.FrontFood
import com.example.myapplication.room.RoomToFullFrontFoodAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
    private val _foodListLiveData = MutableLiveData<List<FrontFood>>()
    val foodListLiveData: LiveData<List<FrontFood>> get() = _foodListLiveData

    fun setFoodList(secondList: List<FrontFood>) {
        _foodListLiveData.postValue(secondList)
    }
    fun getDatabaseEntries(){
        val foodDatabase = MainActivity.getDatabaseInstance()
        val foodBase = foodDatabase.dao()
        var reverseRoomAdapter = RoomToFullFrontFoodAdapter()

        viewModelScope.launch(Dispatchers.IO) {
            val foodRoomInfoList = foodBase.getAllFoods()
            val secondlist = foodRoomInfoList.mapNotNull { reverseRoomAdapter.adapt(it) }

            // Call setFoodList inside the coroutine scope
            setFoodList(secondlist)
        }
    }
}