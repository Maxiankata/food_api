package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.FrontFood
import com.example.myapplication.setRoundedCorners

class   IngredientsAdapter(): RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {
    private val items = ArrayList<FrontFood>()
    var itemClickListener: ItemClickListener<FrontFood>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return IngredientsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun updateItems(newItems: List<FrontFood>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.bind(items[position])
    }
    inner class IngredientsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var image = view.findViewById<ImageView>(R.id.recyclerImage)
        var container = view.findViewById<ImageView>(R.id.container)
        var recipeName = view.findViewById<TextView>(R.id.recipeName)
        fun bind(food: FrontFood) {
            Glide.with(image).load(food.imageUrl).into(image)
            container.setRoundedCorners(30F)
            recipeName.text = food.title
        }
    }
}