package com.example.myapplication.adapters

import com.example.myapplication.data.Food
import com.example.myapplication.data.FrontFood


class FoodToFrontFoodAdapter : Adapter<Food, FrontFood> {
    override fun adapt(t: Food): FrontFood? {
        return FrontFood(t.id, t.title!!, t.image!!)
    }
}
