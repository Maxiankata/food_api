package com.example.myapplication;

import android.app.Application;
import com.example.myapplication.api.RetrofitFoodApiService

class MyApplication : Application() {
    val apiService: RetrofitFoodApiService by lazy {
        RetrofitFoodApiService.getApi()
    }

    override fun onCreate() {
        super.onCreate()
    }
}

fun Application.getApiService() = (this as MyApplication).apiService
