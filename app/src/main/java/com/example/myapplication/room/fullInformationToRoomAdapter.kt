package com.example.myapplication.room

import com.example.myapplication.adapters.Adapter
import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.Response


class FullInformationToRoomAdapter: Adapter<Response.FullInformationRecipe, FoodRoomInfo> {
    override fun adapt(t: Response.FullInformationRecipe): FoodRoomInfo? {
        return FoodRoomInfo(t.id, t.title!!, t.image!!)
    }
}
class RoomToFullFrontFoodAdapter:Adapter<FoodRoomInfo, FrontFood>{
    override fun adapt(t: FoodRoomInfo): FrontFood? {
        return FrontFood(t.id, t.name!!, t.image!!)
    }
}