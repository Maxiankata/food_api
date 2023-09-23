package com.example.myapplication.ui.IngredientsFragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.widget.SearchView
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.data.TextPredictor
import com.example.myapplication.adapters.ItemClickListener
import com.example.myapplication.adapters.OptionRecyclerAdapter
import com.example.myapplication.adapters.TextPredictionAdapter
import com.example.myapplication.data.FrontFood
import com.example.myapplication.databinding.FragmentRecipeNameBinding
import com.example.myapplication.setExplicableRoundedCorners
import com.example.myapplication.setRoundedCorners
import com.example.myapplication.ui.dishSearch.DishViewModel


class DishFragment : Fragment() {
    private var _binding: FragmentRecipeNameBinding? = null
    private val binding get() = _binding!!
    private val handler = Handler(Looper.getMainLooper())

    private lateinit var recipeContainerAdapter: RecipeContainerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeNameBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dishViewModel = ViewModelProvider(this)[DishViewModel::class.java]

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
        binding.apply {
            button1.visibility = GONE
            button.setOnClickListener{
                button.visibility = GONE
                button1.visibility = VISIBLE
            }
            button1.setOnClickListener{
                button.visibility = VISIBLE
                button1.visibility = GONE
            }
        }


        binding.apply {
            imageScroller.setExplicableRoundedCorners(10F, 50F, 10F, 50F)
            textInput.setRoundedCorners(20F)

            textPredictionRecycler.apply {
                setRoundedCorners(10F)
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
                        if (button.visibility == VISIBLE) {
                            query?.let {
                                dishViewModel.fetchFood(query)
                                Log.d("FOUND INGREDIENTS LOL", query.toString())
                            }
                            textPredictionRecycler.visibility = GONE

                        }else{
                            query?.let {
                                dishViewModel.fetchIngredients(query)
                                Log.d("FOUND INGREDIENTS LOL", query.toString())
                            }
                            textPredictionRecycler.visibility = GONE
                        }
                        return true
                    }

                    override fun onQueryTextChange(query: String?): Boolean {

                            query?.let {
                                textPredictionRecycler.visibility = VISIBLE
                                handler.postDelayed({
                                    dishViewModel.fetchPredictionText(query)
                                }, 1000)
                            }

                        return true
                    }
                })
            }
        }
        dishViewModel.autoCompleteText.observe(viewLifecycleOwner){
            Log.d("AutoCompleteData", it.toString())
            binding.apply {
                textPredictionAdapter.updateItems(it as ArrayList<TextPredictor>)

            }
        }
        dishViewModel.recipes.observe(viewLifecycleOwner){
            binding.apply {
                itemAdapter.updateItems(it)
            }
        }

        // Make the fab visible
        (activity as? MainActivity)?.setFabVisibility(GONE)

        // Rest of your code
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Hide the fab when the fragment is destroyed
        (activity as? MainActivity)?.setFabVisibility(GONE)
    }

}