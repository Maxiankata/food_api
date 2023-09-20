package com.example.myapplication.ui

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

class OptionRecyclerAdapter: RecyclerView.Adapter<OptionRecyclerAdapter.OptionRecyclerViewHolder>() {
    private val items = ArrayList<FrontFood>()
    var itemClickListener: ItemClickListener<FrontFood>? = null
    inner class OptionRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view)  {
        var image = view.findViewById<ImageView>(R.id.searchEditText)
        var container = view.findViewById<View>(R.id.container)
        var title = view.findViewById<TextView>(R.id.recipeName)

        fun bind(frontFood: FrontFood) {
            Glide.with(image).load(frontFood.imageUrl).into(image)
//            container.setRoundedCorners(30F)
            title.text = frontFood.title
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


    override fun onBindViewHolder(holder: OptionRecyclerViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

interface ItemClickListener<T> {
    fun onItemClicked(item: T, itemPosition: Int)
}
