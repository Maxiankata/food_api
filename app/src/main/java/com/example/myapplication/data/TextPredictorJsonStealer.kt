package com.example.myapplication.data

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class TextPredictorJsonStealer(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String? = null,

    )
