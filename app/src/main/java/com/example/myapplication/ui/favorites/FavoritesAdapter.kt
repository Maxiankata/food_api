package com.example.myapplication.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.adapters.ItemClickListener
import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.Response.FullInformationRecipe
import com.example.myapplication.setRoundedCorners

class FavoritesAdapter: RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    private val items = ArrayList<FullInformationRecipe>()
    var itemClickListener: ItemClickListener<FullInformationRecipe>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesAdapter.FavoritesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)
        return FavoritesViewHolder(itemView)    }

    override fun onBindViewHolder(holder: FavoritesAdapter.FavoritesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<FullInformationRecipe>) {
        val oldList: List<FullInformationRecipe> = ArrayList(items)

        items.clear()
        items.addAll(newItems)

        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = oldList.size

            override fun getNewListSize(): Int = newItems.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition].id == newItems[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] == newItems[newItemPosition]
            }
        }).dispatchUpdatesTo(this)


    }
    inner class FavoritesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image = view.findViewById<ImageView>(R.id.recyclerImage)
        var container = view.findViewById<ImageView>(R.id.container)
        var recipeName = view.findViewById<TextView>(R.id.recipeName)
        fun bind(food: FullInformationRecipe) {
            Glide.with(image).load(food.image).into(image)
            container.setRoundedCorners(30F)
//            recipeName.text = food.title
        }
    }
}