package com.example.myapplication.room

import com.example.myapplication.adapters.Adapter
import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.Response


class FullInformationToRoomAdapter: Adapter<Response.FullInformationRecipe, FoodRoomInfo> {
    override fun adapt(t: Response.FullInformationRecipe): FoodRoomInfo? {
        return FoodRoomInfo(t.id, t.title!!, t.image!!)
    }
}
class RoomToFullInformationAdapter:Adapter<FoodRoomInfo, Response.FullInformationRecipe>{
    override fun adapt(t: FoodRoomInfo): Response.FullInformationRecipe? {
        return Response.FullInformationRecipe(t.id, t.name!!, t.image!!)
    }
}