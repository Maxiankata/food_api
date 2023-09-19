package com.example.myapplication.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.setExplicableRoundedCorners

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModels()
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.apply {

            homeViewModel.recipe.observe(viewLifecycleOwner){
                binding.funfact.text = it.title
                Glide.with(requireContext())
                    .load(it.imageUrl)
                    //.transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.imageScroller)
            }

            imageScroller.apply {
                setExplicableRoundedCorners(120F, 10F, 120F, 10F)
            }
            ingredients.apply {
                setExplicableRoundedCorners(80f, 0f, 0f, 0f)
                setOnClickListener {
                    view?.let {
                        Navigation.findNavController(it).navigate(R.id.IngredientSearch)
                    }
                }
            }

            dishSearch.apply {
                setExplicableRoundedCorners(0f, 80f, 0f, 0f)

                setOnClickListener {
                    view?.let {
                        Navigation.findNavController(it).navigate(R.id.fragmentDishName)
                    }
                }
            }
            nutrientsSearch.apply {

                setExplicableRoundedCorners(0f, 0f, 0f, 80f)
                setOnClickListener {
                    view?.let {
                        Navigation.findNavController(it).navigate(R.id.fragmentNutrientSearch)
                    }

                }
            }
            weeklyRecommended.apply {
                setExplicableRoundedCorners(0f, 0f, 80f, 0f)
                setOnClickListener {
                    view?.let { Navigation.findNavController(it).navigate(R.id.fragmentDishName) }
                }
            }



        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}