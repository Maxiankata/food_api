package com.example.myapplication.ui.dish_name

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.adapters.IngredientsAdapter
import com.example.myapplication.adapters.ItemClickListener
import com.example.myapplication.adapters.OptionRecyclerAdapter
import com.example.myapplication.adapters.TextPredictionAdapter
import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.TextPredictor
import com.example.myapplication.databinding.FragmentDishNameBinding
import com.example.myapplication.ui.Ingredients.IngredientsViewModel


class FragmentDishName : Fragment() {

    private var _binding: FragmentDishNameBinding? = null
    private val binding get() = _binding!!

    private val handler = Handler(Looper.getMainLooper())

    private lateinit var ingredientsAdapter: IngredientsAdapter
    private val dishNameViewModel: DishNameViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDishNameBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dishNameViewModel = ViewModelProvider(this)[DishNameViewModel::class.java]

        val textPredictionAdapter = TextPredictionAdapter().apply {
            itemClickListener = object : ItemClickListener<TextPredictor> {
                override fun onItemClicked(item: TextPredictor, itemPosition: Int) {
                    binding.textInput.setQuery(item.name, true)
                }
            }
        }
        val itemAdapter = OptionRecyclerAdapter().apply {
            itemClickListener = object : ItemClickListener<FrontFood> {
                override fun onItemClicked(item: FrontFood, itemPosition: Int) {
                    Log.d("SENDING ID", item.id.toString())
                    findNavController().navigate(R.id.action_IngredientSearch_to_blahblah, bundleOf("recipe_id" to item.id))
                }
            }
        }
        dishNameViewModel._foods.observe(viewLifecycleOwner){

        }
        binding.apply {

            textPredictionRecycler.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = textPredictionAdapter
            }
            itemRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = itemAdapter
            }
            textInput.apply {
                setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.let{
                            dishNameViewModel.fetchFood(query)

                        }
                        textPredictionRecycler.visibility= View.GONE
                        return true
                    }

                    override fun onQueryTextChange(query: String?): Boolean {
                        query?.let {
                            textPredictionRecycler.visibility= View.VISIBLE
                            handler.postDelayed({
                                dishNameViewModel.fetchPredictionText(query)
                            }, 1000)
                        }
                        return true
                    }
                })
            }
        }
        dishNameViewModel.autoCompleteText.observe(viewLifecycleOwner){
            Log.d("AutoCompleteData", it.toString())
            binding.apply {
                textPredictionAdapter.updateItems(it as ArrayList<TextPredictor>)

            }
        }
        dishNameViewModel.recipes.observe(viewLifecycleOwner){
            binding.apply {
                itemAdapter.updateItems(it)
            }
        }

        // Make the fab visible
        (activity as? MainActivity)?.setFabVisibility(View.GONE)

        // Rest of your code
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Hide the fab when the fragment is destroyed
        (activity as? MainActivity)?.setFabVisibility(View.GONE)
    }


}