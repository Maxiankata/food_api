package com.example.myapplication.api

import NutritionResponseToFrontFoodAdapter
import com.example.myapplication.adapters.FoodToFrontFoodAdapter
import com.example.myapplication.adapters.FullInformationAdapter
import com.example.myapplication.adapters.IngredientResponseToFrontFood
//import com.example.myapplication.adapters.InstructionAdapter
import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.FoodResponse
import com.example.myapplication.data.TextPredictor
import com.example.myapplication.adapters.TextPredictionAdapterAdapter
import com.example.myapplication.data.AnalyzedInstruction
import com.example.myapplication.data.Food
import com.example.myapplication.data.FoodFullInformation

import com.example.myapplication.data.ApiRandomTrivia
import com.example.myapplication.data.RandomResponse
import com.example.myapplication.data.Step
//import com.example.myapplication.data.Steps
import com.example.myapplication.data.TextPredictorJsonStealer
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class RetrofitFoodApiService : FoodApiService {

    companion object {
//        const val API_KEY = "e2cf35ef206c4578a860449e2bf7e65a"
//        const val API_KEY = "12e762759f344271b7abc1a4da9400e8" //ended for today
//        const val API_KEY = "0e0a2be67fe14660b09c10952fe86678" //ended for today
        const val API_KEY = "39e85332726b4ce7a97b9130ee77b9ac"
        const val API_HOST = "https://api.spoonacular.com/"

        private var apiSingleton: RetrofitFoodApiService? = null
        private var adapter = FoodToFrontFoodAdapter()
        var predictionAdapter = TextPredictionAdapterAdapter()
        var fullInformationAdapter = FullInformationAdapter()
        var ingredientResponseToFrontFood = IngredientResponseToFrontFood()
        var nutritionResponseToFrontFoodAdapter = NutritionResponseToFrontFoodAdapter()
//        var instructionAdapter = InstructionAdapter()


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

    override suspend fun getFoodByComplexSearch(query: String): List<FrontFood> =
        foodApi.getFoodByComplexSearch(API_KEY, query).results.mapNotNull { adapter.adapt(it!!) }

    override suspend fun getRandomRecipe(): List<FrontFood> = foodApi.getRandomRecipe(API_KEY).results.mapNotNull { adapter.adapt(it!!) }

    override suspend fun getPrediction(query: String): List<TextPredictor> =
        foodApi.getPredictionText(API_KEY, query).mapNotNull { predictionAdapter.adapt(it) }

    override suspend fun getRecipeById(id: Int): FoodFullInformation =
        fullInformationAdapter.adapt(foodApi.getRecipeById(id, API_KEY))!!

    override suspend fun findByNutrients(minCarbs: Int, maxCarbs: Int, minFat: Int, maxFat: Int, minCalories: Int, maxCalories: Int, minProtein: Int, maxProtein: Int
    ): List<FrontFood> {
        TODO("Not yet implemented")
    }

    override suspend fun findByIngredients(ingredient: String): List<FrontFood> =
        foodApi.getRecipesByIngredients(API_KEY, ingredient)
            .mapNotNull { adapter.adapt(it) }

    override suspend fun getRecipeInstructionsById(id: Int): List<Step> =
        foodApi.getRecipeInstructionsById(id, API_KEY).first().steps

    override suspend fun getRandomTrivia(): String = foodApi.getRandomFoodTrivia(API_KEY).text?:"no trivia sir"

}

interface FoodApi {
    @GET("recipes/complexSearch")
    suspend fun getFoodByComplexSearch(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String,
        @Query("number") number: Int = 1
    ): FoodResponse

    @GET("recipes/random")
    suspend fun getRandomRecipe(@Query("apiKey") apiKey: String, @Query("number") number: Int = 1): RandomResponse


    @GET("recipes/autocomplete")
    suspend fun getPredictionText(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String,
        @Query("number") number: Int = 5
    ): List<TextPredictorJsonStealer>

    @GET("recipes/{id}/information")
    suspend fun getRecipeById(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String
    ): FoodFullInformation

    @GET("recipes/findByIngredients")
    suspend fun getRecipesByIngredients(@Query("apiKey") apiKey: String, @Query("ingredients") query: String, @Query("number") number: Int = 1): List<Food>


    @GET("recipes{id}/analyzedInstructions")
    suspend fun getRecipeInstructionsById(@Path("id") id: Int, @Query("apiKey") apiKey: String): List<AnalyzedInstruction>

    @GET("food/trivia/random")
    suspend fun getRandomFoodTrivia(@Query("apiKey") apiKey: String): ApiRandomTrivia


    @GET("recipes/findByNutrients")
    suspend fun findByNutrients(
        @Query("apiKey") apiKey: String,
        @Query("number") number: Int = 1,
        @Query("minCarbs") minCarbs: Int?,
        @Query("maxCarbs") maxCarbs: Int?,
        @Query("minFat") minFat: Int?,
        @Query("maxFat") maxFat: Int?,
        @Query("minCalories") minCalories:Int?,
        @Query("maxCalories") maxCalories:Int?,
        @Query("minProtein") minProtein:Int?,
        @Query("maxProtein") maxProtein:Int?
        ): List<Food>

}


