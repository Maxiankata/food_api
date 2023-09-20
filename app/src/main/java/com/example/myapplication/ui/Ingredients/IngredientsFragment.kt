package com.example.myapplication.ui.Ingredients

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.widget.SearchView
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.RoundedCorners
import com.example.myapplication.adapters.IngredientsAdapter
import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.TextPredictor
import com.example.myapplication.databinding.FragmentIngredientsBinding
import com.example.myapplication.adapters.ItemClickListener
import com.example.myapplication.adapters.OptionRecyclerAdapter
import com.example.myapplication.adapters.TextPredictionAdapter
import com.example.myapplication.ui.home.HomeViewModel


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
        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        return binding.root
    }



        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            val homeViewModel = ViewModelProvider(this)[IngredientsViewModel::class.java]
            homeViewModel.recipe.observe(viewLifecycleOwner){
                binding.imageFoodTitle.text = it?.title
                Glide.with(requireContext())
                    .load(it?.imageUrl)

//                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.imageScroller)
            }

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

                    }
                }
            }
//            textPredictionRecycler?.apply {
//                layoutManager = LinearLayoutManager(requireContext())
//                adapter = textPredictionAdapter
//            }
//            itemRecyclerView!!.apply {
//                layoutManager = LinearLayoutManager(requireContext())
//                adapter = itemAdapter
//            }

            binding.apply {
                imageScroller.apply {
                    RoundedCorners(20F)
                }
                textPredictionRecycler.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = textPredictionAdapter
                }
                itemRecyclerView.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = itemAdapter
                }
                textInput.apply {
                    setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            query?.let{
                                ingredientsViewModel.fetchFood(query)
                            }
                            return true
                        }

                        override fun onQueryTextChange(query: String?): Boolean {
                            query?.let {
                                ingredientsViewModel.fetchPredictionText(query)
                            }
                            return true
                        }
                    })
                }
            }
            ingredientsViewModel.autoCompleteText.observe(viewLifecycleOwner){
                Log.d("AutoCompleteData", it.toString())
                binding.apply {
                    textPredictionAdapter.updateItems(it as ArrayList<TextPredictor>)

                }
            }
            ingredientsViewModel.recipes.observe(viewLifecycleOwner){
                binding.apply {
                    itemAdapter.updateItems(it)
                }
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