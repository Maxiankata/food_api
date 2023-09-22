package com.example.myapplication.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.setRoundedCorners

class IngredientsViewPagerAdapter(ingredients: List<String> = emptyList()) : RecyclerView.Adapter<IngredientsViewPagerAdapter.Pager2ViewHolder>() {

    private val items = mutableListOf<String>().apply { addAll(ingredients) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewPagerAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.ingredient_text, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun updateItems(newItems: List<String>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        val ingredient = items[position]
        if (ingredient.isNotEmpty()){
            Log.d("INGREDIENT LOADING", ingredient)
            holder.ingredientView.text = ingredient
        }

    }
    class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ingredientView: TextView = itemView.findViewById(R.id.ingredient_text)

    }
}