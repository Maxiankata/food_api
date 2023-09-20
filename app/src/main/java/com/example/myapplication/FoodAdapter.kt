package com.example.myapplication

import com.example.myapplication.adapters.Adapter
import com.example.myapplication.data.Food
import com.example.myapplication.data.FrontFood


class FoodAdapter : Adapter<Food, FrontFood> {

    override fun adapt(t: Food): FrontFood? {
        return FrontFood(t.id, t.title!!, t.image!!)
    }
}
