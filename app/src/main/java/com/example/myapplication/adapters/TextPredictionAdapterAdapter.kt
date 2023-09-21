package com.example.myapplication.adapters

import com.example.myapplication.data.Food
import com.example.myapplication.data.TextPredictor
import com.example.myapplication.data.TextPredictorJsonStealer

class TextPredictionAdapterAdapter: Adapter<TextPredictorJsonStealer, TextPredictor> {
    override fun adapt(t: TextPredictorJsonStealer): TextPredictor? {
        return if(t.title == null) null else TextPredictor(id = t.id, name = t.title)
    }
}