package com.example.myapplication.api

import NutritionResponseToFrontFoodAdapter
import android.util.Log
import com.example.myapplication.adapters.FoodToFrontFoodAdapter
import com.example.myapplication.adapters.FullInformationAdapter
import com.example.myapplication.adapters.IngredientResponseToFrontFood
import com.example.myapplication.adapters.InstructionAdapter
import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.RecipeResponse
import com.example.myapplication.data.TextPredictor
import com.example.myapplication.adapters.TextPredictionAdapterAdapter
import com.example.myapplication.data.FoodFullInformation
import com.example.myapplication.data.IngredientResponse
//import com.example.myapplication.data.Response.Steps
import com.example.myapplication.data.Response.FullInformationRecipe
import com.example.myapplication.data.FoodFullInformation.ApiInstructions
import com.example.myapplication.data.Steps
//import com.example.myapplication.data.ApiResponse
import com.example.myapplication.data.TextPredictorJsonStealer
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class RetrofitFoodApiService : FoodApiService {

    companion object {
        const val API_KEY = "e2cf35ef206c4578a860449e2bf7e65a"
//        const val API_KEY = "12e762759f344271b7abc1a4da9400e8"
//        const val API_KEY = "0e0a2be67fe14660b09c10952fe86678"

        const val API_HOST = "https://api.spoonacular.com/recipes/"

        private var apiSingleton: RetrofitFoodApiService? = null
        private var adapter = FoodToFrontFoodAdapter()
        var predictionAdapter = TextPredictionAdapterAdapter()
        var fullInformationAdapter = FullInformationAdapter()
        var ingredientResponseToFrontFood = IngredientResponseToFrontFood()
        var nutritionResponseToFrontFoodAdapter = NutritionResponseToFrontFoodAdapter()
        var instructionAdapter = InstructionAdapter()


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
//    private val favoritesEntryFlow = flow { dataSource.getDogs().forEach { emit(it) } }
//        .mapNotNull { dogEntryToDogtionaryEntryAdapter.adapt(it) }
//        .aggregate()
//        .shareIn(MainScope(), SharingStarted.Lazily, 1)
//
//    override suspend fun getDogtionaryEntriesFlow(): Flow<List<FullInformationRecipe>> = favoritesEntryFlow

    override suspend fun getFoodByComplexSearch(query: String): List<FrontFood> =
        foodApi.getFoodByComplexSearch(API_KEY, query).results.mapNotNull { adapter.adapt(it!!) }

    override suspend fun getRandomRecipe(): FullInformationRecipe =
        fullInformationAdapter.adapt(foodApi.getRandomRecipe(API_KEY))!!

    override suspend fun getPrediction(query: String): List<TextPredictor> =
        foodApi.getPredictionText(API_KEY, query).mapNotNull { predictionAdapter.adapt(it) }

    override suspend fun getRecipeById(id: Int): FullInformationRecipe =
        fullInformationAdapter.adapt(foodApi.getRecipeById(id, API_KEY))!!
            .also { Log.d("SPAS", it.toString()) }

    override suspend fun findByNutrients(
        minCarbs: Int,
        maxCarbs: Int,
        minFat: Int,
        maxFat: Int,
        minCalories: Int,
        maxCalories: Int,
        minProtein: Int,
        maxProtein: Int
    ): List<FrontFood> {
        TODO("Not yet implemented")
    }

    override suspend fun findByIngredients(query: String): List<FrontFood> =
        foodApi.findByIngredients(API_KEY, query)
            .mapNotNull { ingredientResponseToFrontFood.adapt(it) }
            .also { Log.d("FETCHING INGREDIENTS", it.toString()) }

    override suspend fun getRecipeBulk(): Flow<List<FullInformationRecipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeInstructionsById(id: Int): List<Steps> =
        foodApi.getRecipeInstructionsById(id, API_KEY).first().steps.mapNotNull { instructionAdapter.adapt(it) }



}

interface FoodApi {
    @GET("complexSearch")
    suspend fun getFoodByComplexSearch(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String,
        @Query("number") number: Int = 1
    ): RecipeResponse

    @GET("random")
    suspend fun getRandomRecipe(
        @Query("apiKey") apiKey: String,
        @Query("number") number: Int = 1
    ): FoodFullInformation

    @GET("autocomplete")
    suspend fun getPredictionText(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String,
        @Query("number") number: Int = 5
    ): List<TextPredictorJsonStealer>

    @GET("{id}/information")
    suspend fun getRecipeById(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String
    ): FoodFullInformation

    @GET("findByIngredients")
    suspend fun findByIngredients(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String,
        @Query("number") number: Int = 1
    ): List<IngredientResponse>

    @GET("informationBulk")
    suspend fun getInformationBulk(
        @Query("apiKey") apiKey: String,
        @Query("number") number: Int = 1
    ): List<FullInformationRecipe>

    @GET("recipes{id}/analyzedInstructions")
    suspend fun getRecipeInstructionsById(@Path("id") id: Int, @Query("apiKey") apiKey: String): List<ApiInstructions>



//    @GET("findByNutrients")
//    suspend fun findByNutrients(
//        @Query("apiKey") apiKey: String,
//        @Query("number") number: Int = 1,
//        @Query("minCarbs") minCarbs: Int?,
//        @Query("maxCarbs") maxCarbs: Int?,
//        @Query("minFat") minFat: Int?,
//        @Query("maxFat") maxFat: Int?,
//        @Query("minCalories") minCalories:Int?,
//        @Query("maxCalories") maxCalories:Int?,
//        @Query("minProtein") minProtein:Int?,
//        @Query("maxProtein") maxProtein:Int?
//
//
//        ): RecipeResponse

}


