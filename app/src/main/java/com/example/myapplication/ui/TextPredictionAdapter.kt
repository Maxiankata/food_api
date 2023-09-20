package com.example.myapplication.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.TextPredictor
import com.example.myapplication.setRoundedCorners

class TextPredictionAdapter: RecyclerView.Adapter<TextPredictionAdapter.TextPredictionViewHolder>() {
    private val items = ArrayList<FrontFood>()
    var itemClickListener: ItemClickListener<TextPredictor>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TextPredictionAdapter.TextPredictionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_prediction, parent, false)
        return TextPredictionViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TextPredictionAdapter.TextPredictionViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
    inner class TextPredictionViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var textPrediction = view.findViewById<TextView>(R.id.search_prediction)
        fun bind(FrontFood:FrontFood){
            fun bind(autoComplete: TextPredictor) {
                Log.d("BINDING DATA", autoComplete.toString())
                textPrediction.text = autoComplete.name
            }
        }

    }
}