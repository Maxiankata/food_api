package com.example.myapplication.adapters
import com.example.myapplication.data.FoodFullInformation
import com.example.myapplication.data.Response

class InstructionAdapter:Adapter<FoodFullInformation.ApiInstructions.ApiSteps, Response.FullInformationRecipe.Instructions.Steps> {
    override fun adapt(t: FoodFullInformation.ApiInstructions.ApiSteps): Response.FullInformationRecipe.Instructions.Steps? {
        return if(t.number == null || t.instruction == null){
            null
        }else{
            Response.FullInformationRecipe.Instructions.Steps(
                number = t.number,
                step = t.instruction
            )
        }
    }

}
