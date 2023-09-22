package com.example.myapplication.adapters

import com.example.myapplication.data.Food
import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.NutritionResponse

class NutritionResponseToFrontFoodAdapter:Adapter<NutritionResponse, FrontFood> {
    override fun adapt(t: NutritionResponse): FrontFood? {
            return FrontFood(t.id, t.title!!, t.image!!)
    }
}