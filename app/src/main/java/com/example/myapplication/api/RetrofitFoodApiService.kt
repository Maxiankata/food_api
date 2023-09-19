//package com.example.myapplication.api
//
//import com.example.myapplication.data.Food
//import com.example.myapplication.data.FoodEntry
//import com.example.myapplication.data.FoodEntryDao
//import com.example.myapplication.data.FoodImageSearch
//import com.example.myapplication.data.FoodItem
//import com.example.myapplication.data.FoodToFoodEntryAdapter
//import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.mapNotNull
//import kotlinx.serialization.json.Json
//import okhttp3.MediaType.Companion.toMediaType
//import retrofit2.Retrofit
//import retrofit2.http.GET
//import retrofit2.http.Header
//import retrofit2.http.Path
//import retrofit2.http.Query
//import java.io.IOError
//
//class RetrofitFoodApiService (private val foodEntryDao: FoodEntryDao){
//
//    companion object {
//    const val API_KEY = "588f5e88f6af47eaba04f38d98e281ff"
//    const val API_HOST = "https://spoonacular.com/recipes"
//}
//    private val foodApi: FoodApi
//    val foodToFoodEntryAdapter: FoodToFoodEntryAdapter
//
//    init {
//        val contentType = "application/json".toMediaType()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(API_HOST)
//            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType))
//            .build()
//
//        foodApi = retrofit.create(FoodApi::class.java)
//    }
//
//    private suspend fun getDogsInternal(): List<FoodEntry> {
//        return if (foodEntryDao.countAllFoods() == 0) {
//            sync()
//        } else {
//            foodEntryDao.getAllFoods()
//        }
//    }
//    private suspend fun sync(): List<FoodEntry> {
//        // TODO check if sync is already on progress and do not trigger multiple syncs
//        // TODO error handling for first flow "getBreeds"
//        val allFoods =
//            flow {
//                foodApi.getFoodName(API_KEY).take(15).forEach { emit(it) }
//            }
//                .mapNotNull { foodToFoodEntryAdapter.adapt(it) }
//
//
//        foodEntryDao.insertFoods(allFoods)
//        return allFoods
//    }
//    interface FoodApi {
//        @GET("food")
//        suspend fun getFoodName(@Header("x-api-key") apiKey: String): List<FoodItem>
//
//        @GET("food/{food_id}")
//        suspend fun getFoodId(@Header("x-api-key") apiKey: String, @Path("breed_id") breedId:Int): Food
//
//        @GET("images/search")
//        suspend fun getImages(
//            @Header("x-api-key") apiKey: String,
//            @Query("food_id") breedId:Int,
//            @Query("size") size:String = "full",
//            @Query("limit") limit:String = "10"
//        ): List<FoodImageSearch>
//    }
//}
