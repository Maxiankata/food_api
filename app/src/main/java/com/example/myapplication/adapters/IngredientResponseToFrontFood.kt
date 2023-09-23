package com.example.myapplication.adapters

import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.IngredientResponse

class IngredientResponseToFrontFood:Adapter<IngredientResponse, FrontFood> {
    override fun adapt(t: IngredientResponse): FrontFood? {
        return FrontFood(t.id, t.title!!, t.image!!)
    }
}
