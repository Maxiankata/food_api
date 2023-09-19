//package com.example.myapplication.ui.Ingredients
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.core.content.ContextCompat
//import androidx.recyclerview.widget.RecyclerView
//import com.example.myapplication.R
//import com.example.myapplication.data.FrontFood
//import com.example.myapplication.setRoundedCorners
//
//class IngredientsAdapter(): RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {
//    private val items = ArrayList<FrontFood>()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
//        return IngredientsViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//
//    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
//        holder.bind(items[position])
//    }
//    inner class IngredientsViewHolder(view: View) : RecyclerView.ViewHolder(view){
//        var image = view.findViewById<ImageView>(R.id.recyclerImage)
//        var container = view.findViewById<ImageView>(R.id.container)
//        var recipeName = view.findViewById<TextView>(R.id.recipeName)
//        fun bind(food: FrontFood) {
//            image.apply {
//                setImageResource(R.drawable.udon)
//                setRoundedCorners(60F)
//            }
//            container.setRoundedCorners(30F)
//            recipeName.text = food.title
//
//
//        }
//    }
//}