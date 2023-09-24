package com.example.myapplication.room

import com.example.myapplication.adapters.Adapter
import com.example.myapplication.data.FoodFullInformation
import com.example.myapplication.data.FrontFood



class FullInformationToRoomAdapter: Adapter<FoodFullInformation, FoodRoomInfo> {
    override fun adapt(t: FoodFullInformation): FoodRoomInfo? {
        return FoodRoomInfo(t.id, t.title!!, t.image!!)
    }
}
class RoomToFullFrontFoodAdapter:Adapter<FoodRoomInfo, FrontFood>{
    override fun adapt(t: FoodRoomInfo): FrontFood? {
        return FrontFood(t.id, t.name!!, t.image!!)
    }
}