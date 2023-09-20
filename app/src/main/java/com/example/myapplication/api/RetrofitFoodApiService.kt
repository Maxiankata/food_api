package com.example.myapplication.api
import com.example.myapplication.FoodAdapter
import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.RecipeResponse
import com.example.myapplication.data.TextPredictor
import com.example.myapplication.adapters.TextPredictionAdapter
import com.example.myapplication.adapters.TextPredictionAdapterAdapter
import com.example.myapplication.data.TextPredictorJsonStealer
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

class RetrofitFoodApiService : FoodApiService{

    companion object {
    const val API_KEY = "e2cf35ef206c4578a860449e2bf7e65a"
//        const val API_KEY = "12e762759f344271b7abc1a4da9400e8"

        const val API_HOST = "https://api.spoonacular.com/recipes/"

        private var apiSingleton: RetrofitFoodApiService? = null
        private var adapter = FoodAdapter()
        var predictionAdapter = TextPredictionAdapterAdapter()
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


    override suspend fun getFoodByComplexSearch(query: String): List<FrontFood> = foodApi.getItems(API_KEY, query).results.mapNotNull { adapter.adapt(it!!) }
    override suspend fun getRandomRecipe(): FrontFood? = foodApi.getRandomRecipe(API_KEY).results.first()?.let {recipe -> adapter.adapt(recipe) }
    override suspend fun getPrediction(query: String): List<TextPredictor> = foodApi.getPredictionText(API_KEY, query).mapNotNull { predictionAdapter.adapt(it) }


}
interface FoodApi {
    @GET("complexSearch")
    suspend fun getItems(@Query("apiKey") apiKey: String, @Query("query") query: String, @Query("number") number: Int = 1): RecipeResponse
    @GET("random")
    suspend fun getRandomRecipe(@Query("apiKey") apiKey: String, @Query("number") number: Int = 1): RecipeResponse
    @GET("autocomplete")
    suspend fun getPredictionText(@Query("apiKey") apiKey: String, @Query("query") query: String, @Query("number") number: Int = 5): List<TextPredictorJsonStealer>

}