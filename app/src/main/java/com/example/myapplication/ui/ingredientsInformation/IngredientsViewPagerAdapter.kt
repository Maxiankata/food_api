package com.example.myapplication.ui.ingredientsInformation


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.ExtendedIngredient

class IngredientsViewPagerAdapter :
    RecyclerView.Adapter<IngredientsViewPagerAdapter.IngredientsPagerViewHolder>() {
    class IngredientsPagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ingredient = view.findViewById<TextView>(R.id.ingredient_text_field)

        fun bind(ingredients: ExtendedIngredient) {
            ingredient.text = ingredients.original.toString()
        }
    }

    var items = ArrayList<ExtendedIngredient>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = IngredientsPagerViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredient_view_pager, parent, false)
    )


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: IngredientsPagerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateItems(newIngredients: List<ExtendedIngredient>?) {
        items.clear()
        items.addAll(newIngredients!!)
        notifyDataSetChanged()
    }
}
