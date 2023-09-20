package com.example.myapplication.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.TextPredictor

class TextPredictionAdapter: RecyclerView.Adapter<TextPredictionAdapter.TextPredictionViewHolder>() {
    private val items = ArrayList<TextPredictor>()
    var itemClickListener: ItemClickListener<TextPredictor>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TextPredictionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_prediction, parent, false)
        return TextPredictionViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TextPredictionViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }
    fun updateItems(newItems: List<TextPredictor>) {
        val oldList: List<TextPredictor> = ArrayList(items)

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
    override fun getItemCount(): Int = items.size
    inner class TextPredictionViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var textPrediction: TextView = view.findViewById(R.id.search_prediction)
        fun bind(textPredictor:TextPredictor){

                textPrediction.apply {
                    text = textPredictor.name
                    setOnClickListener {
                        itemClickListener?.onItemClicked(textPredictor, layoutPosition)

                    }
                }
        }

    }
}