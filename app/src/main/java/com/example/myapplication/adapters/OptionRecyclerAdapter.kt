package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.FrontFood
import com.example.myapplication.setExplicableRoundedCorners
import com.example.myapplication.setRoundedCorners

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
                .load(frontFood.image)
                .into(recyclerImage)
            recyclerImage.setExplicableRoundedCorners(40F, 10F, 40F, 10F)
//            Glide.with(image).load(frontFood.imageUrl).into(image)
            container.setRoundedCorners(30F)
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
        val oldList: List<FrontFood> = ArrayList(items)

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
    override fun getItemCount(): Int {
       return items.size
    }
}

interface ItemClickListener<T> {
    fun onItemClicked(item: T, itemPosition: Int)
}
