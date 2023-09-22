package com.example.myapplication;

import android.app.Application;
import com.example.myapplication.api.RetrofitFoodApiService
import com.example.myapplication.data.Storage

class MyApplication : Application() {
    val apiService: RetrofitFoodApiService by lazy {
        RetrofitFoodApiService.getApi()
    }
    lateinit var storage: Storage
        private set
    override fun onCreate() {
        super.onCreate()
        storage = Storage(this)
    }
}

fun Application.getApiService() = (this as MyApplication).apiService
fun Application.getStorage() = (this as MyApplication).storage
