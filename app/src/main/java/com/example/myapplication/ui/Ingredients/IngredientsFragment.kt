package com.example.myapplication.ui.Ingredients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentIngredientsBinding


class IngredientsFragment : Fragment() {
    private var _binding: FragmentIngredientsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
//    private lateinit var ingredientsAdapter: IngredientsAdapter
    ////   private val ingredientsViewModel:IngredientsViewModel by viewModels()


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