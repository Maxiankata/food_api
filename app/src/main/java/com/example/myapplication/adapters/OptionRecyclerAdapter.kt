package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.FoodFullInformation
import com.example.myapplication.data.FrontFood

class OptionRecyclerAdapter: RecyclerView.Adapter<OptionRecyclerAdapter.OptionRecyclerViewHolder>() {
    private val items = ArrayList<FrontFood>()
    var itemClickListener: ItemClickListener<FrontFood>? = null

    override fun onBindViewHolder(holder: OptionRecyclerViewHolder, position: Int) {
        holder.bind(items[position])
    }
    inner class OptionRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view)  {
        var recyclerImage = view.findViewById<ImageView>(R.id.recyclerImage)
        var container = view.findViewById<View>(R.id.container)
        var title = view.findViewById<TextView>(R.id.recipeName)

        fun bind(frontFood: FrontFood) {
            Glide.with(recyclerImage)
                .load(frontFood.imageUrl)
                .into(recyclerImage)

//            Glide.with(image).load(frontFood.imageUrl).into(image)
//            container.setRoundedCorners(30F)
            title.text = frontFood.title

            itemView.setOnClickListener {
                itemClickListener?.onItemClicked(frontFood, absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return OptionRecyclerViewHolder(view)
    }
    fun updateItems(newItems: List<FrontFood>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
       return items.size
    }



}

interface ItemClickListener<T> {
    fun onItemClicked(item: T, itemPosition: Int)
}
