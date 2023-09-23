package com.example.myapplication.adapters

//import com.example.myapplication.data.ApiSteps
import com.example.myapplication.data.FoodFullInformation
import com.example.myapplication.data.Response
//import com.example.myapplication.data.Steps

class InstructionAdapter:Adapter<FoodFullInformation.ApiInstructions.ApiSteps, Response.FullInformationRecipe.Steps> {
    override fun adapt(t: FoodFullInformation.ApiInstructions.ApiSteps): Response.FullInformationRecipe.Steps? {
        return if(t.number == null || t.instruction == null){
            null
        }else{
            Response.FullInformationRecipe.Steps(
                number = t.number,
                step = t.instruction
            )
        }
    }

}
