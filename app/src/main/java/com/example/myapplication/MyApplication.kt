package com.example.myapplication;

import android.app.Application;
import androidx.room.Room
import com.example.myapplication.api.RetrofitFoodApiService
import com.example.myapplication.room.FoodDB

class MyApplication : Application() {
    val apiService: RetrofitFoodApiService by lazy {
        RetrofitFoodApiService.getApi()
    }
    companion object {

    }
    override fun onCreate() {
        super.onCreate()
    }

}

fun Application.getApiService() = (this as MyApplication).apiService
