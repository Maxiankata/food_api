package com.example.myapplication.api
import com.example.myapplication.FoodAdapter
import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.RecipeResponse
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

class RetrofitFoodApiService : FoodApiService{

    companion object {
    const val API_KEY = "588f5e88f6af47eaba04f38d98e281ff"
    const val API_HOST = "https://api.spoonacular.com/recipes/"

        private var apiSingleton: RetrofitFoodApiService? = null
        private var adapter = FoodAdapter()

        // Corrected the return type to RetrofitFoodApiService
        fun getApi(): RetrofitFoodApiService {
            return apiSingleton ?: RetrofitFoodApiService().also {
                apiSingleton = it
            }
        }
}
    private val foodApi: FoodApi
    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(API_HOST)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        foodApi = retrofit.create()
    }


    override suspend fun getRecipesByComplexSearch(query: String): List<FrontFood> = foodApi.getItems(API_KEY, query).results.mapNotNull { adapter.adapt(it!!) }
    override suspend fun getRandomRecipe(): FrontFood? = foodApi.getRandomRecipe(API_KEY).results.first()?.let{ adapter.adapt(it)}


}
interface FoodApi {
    @GET("complexSearch")
    suspend fun getItems(@Query("apiKey") apiKey: String, @Query("query") query: String, @Query("number") number: Int = 1): RecipeResponse
    @GET("random")
    suspend fun getRandomRecipe(@Query("apiKey") apiKey: String, @Query("number") number: Int = 1): RecipeResponse
}