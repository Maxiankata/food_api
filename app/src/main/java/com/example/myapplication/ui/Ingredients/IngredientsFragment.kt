package com.example.myapplication.ui.Ingredients

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.data.TextPredictor
import com.example.myapplication.databinding.FragmentIngredientsBinding
import com.example.myapplication.ui.ItemClickListener
import com.example.myapplication.ui.TextPredictionAdapter


class IngredientsFragment : Fragment() {
    private var _binding: FragmentIngredientsBinding? = null

    private val handler = Handler(Looper.getMainLooper())

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var ingredientsAdapter: IngredientsAdapter
       private val ingredientsViewModel:IngredientsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredients, container, false)

    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            val textPredictionAdapter = TextPredictionAdapter().apply {
                itemClickListener = object : ItemClickListener<TextPredictor> {
                    override fun onItemClicked(item: TextPredictor, itemPosition: Int) {
                        Log.d("CLICKED", "WOW")
                        //submit is whether to search immediately after youve autofilled or keep typing and wait for enter to be pressed
                        binding.searchEditText.setQuery(item.name, false)
                    }
                }
            }
            binding.apply {
                layoutManager
            }

        // Make the fab visible
        (activity as? MainActivity)?.setFabVisibility(View.GONE)
//        ingredientsAdapter = IngredientsAdapter()
//        binding?.itemRecyclerView?.apply {
//            layoutManager = GridLayoutManager(context, 1)
//            adapter = IngredientsAdapter()
//        }
        // Rest of your code
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Hide the fab when the fragment is destroyed
        (activity as? MainActivity)?.setFabVisibility(View.GONE)
    }

}