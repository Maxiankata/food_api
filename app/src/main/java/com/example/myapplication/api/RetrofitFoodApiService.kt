package com.example.myapplication.api
import android.util.Log
import com.example.myapplication.FoodAdapter
import com.example.myapplication.adapters.FullInformationAdapter
import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.RecipeResponse
import com.example.myapplication.data.TextPredictor
import com.example.myapplication.adapters.TextPredictionAdapterAdapter
import com.example.myapplication.data.FullInformationRecipe
import com.example.myapplication.data.RandomResponse
import com.example.myapplication.data.TextPredictorJsonStealer
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class RetrofitFoodApiService : FoodApiService{

    companion object {
//    const val API_KEY = "e2cf35ef206c4578a860449e2bf7e65a"
        const val API_KEY = "12e762759f344271b7abc1a4da9400e8"

        const val API_HOST = "https://api.spoonacular.com/recipes/"

        private var apiSingleton: RetrofitFoodApiService? = null
        private var adapter = FoodAdapter()
        var predictionAdapter = TextPredictionAdapterAdapter()
        var fullInformationAdapter = FullInformationAdapter()

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
    override suspend fun getRandomRecipe(): FullInformationRecipe = fullInformationAdapter.adapt(foodApi.getRandomRecipe(API_KEY).first().randomResponse.first())!!
    override suspend fun getPrediction(query: String): List<TextPredictor> = foodApi.getPredictionText(API_KEY, query).mapNotNull { predictionAdapter.adapt(it) }

    override suspend fun getRecipeById(id:Int): FullInformationRecipe = fullInformationAdapter.adapt(foodApi.getRecipeById(id, API_KEY).first().randomResponse.first())!!.also { Log.d("SPAS", it.toString()) }

}
interface FoodApi {
    @GET("complexSearch")
    suspend fun getItems(@Query("apiKey") apiKey: String, @Query("query") query: String, @Query("number") number: Int = 5): RecipeResponse
    @GET("random")
    suspend fun getRandomRecipe(@Query("apiKey") apiKey: String, @Query("number") number: Int = 1): ArrayList<RandomResponse>
    @GET("autocomplete")
    suspend fun getPredictionText(@Query("apiKey") apiKey: String, @Query("query") query: String, @Query("number") number: Int = 5): List<TextPredictorJsonStealer>
    @GET("{id}/information")
    suspend fun getRecipeById(@Path("id") id:Int, @Query("apiKey") apiKey: String):List<RandomResponse>
}