package com.example.myapplication.data

import com.google.gson.annotations.SerializedName
@kotlinx.serialization.Serializable

data class FoodFullInformation(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("readyInMinutes") val readyInMinutes:Int? = null,
    @SerializedName("dairyFree") val dairyFree:Boolean? = null,
    @SerializedName("analyzedInstructions") val analyzedInstructions:ArrayList<Pair<Int?, String>>?=null,
    @SerializedName("ingredients/name") val ingredients:List<String>?=null
)
