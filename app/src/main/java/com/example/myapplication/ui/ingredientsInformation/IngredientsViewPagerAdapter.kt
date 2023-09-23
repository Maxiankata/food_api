package com.example.myapplication.ui.ingredientsInformation

import com.example.myapplication.data.Ingredients

//class IngredientsViewPagerAdapter :
//    RecyclerView.Adapter<IngredientsViewPagerAdapter.IngredientsViewHolder>() {
//    var items = ArrayList<Ingredients>()
//
//    class IngredientsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val ingredientName = view.findViewById<TextView>(R.id.ingredient_text)
//        fun bind(ingredients: Ingredients) {
//            ingredientName.text = ingredients.name
//        }
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): IngredientsViewHolder = IngredientsViewHolder(
//        LayoutInflater.from(parent.context)
//            .inflate(R.layout.ingredient_text, parent, false)
//    )
//
//
//    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
//        holder.bind(items[position])
//    }
//
//    override fun getItemCount(): Int = items.size
//
//    fun updateItems(newIngredients: List<Ingredients>?) {
//        items.clear()
//        items.addAll(newIngredients!!)
//        notifyDataSetChanged()
//    }
//}
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.Response
import com.example.myapplication.data.Steps

//import com.example.myapplication.data.Response.Steps

class IngredientsViewPagerAdapter :
    RecyclerView.Adapter<IngredientsViewPagerAdapter.IngredientsPagerViewHolder>() {
    class IngredientsPagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val stepNumber = view.findViewById<TextView>(R.id.ingredient_text_field)
        fun bind(ingredients: Ingredients) {
            stepNumber.text = ingredients.name
        }
    }

    var items = ArrayList<Ingredients>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = IngredientsPagerViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredient_text, parent, false)
    )


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: IngredientsPagerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateItems(newIngredients: List<Ingredients>?) {
        items.clear()
        items.addAll(newIngredients!!)
        notifyDataSetChanged()
    }
}
