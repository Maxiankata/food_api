package com.example.myapplication.ui.extendedInformation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.FragmentExtendedInformationBinding
import com.example.myapplication.setRoundedCorners
import kotlin.math.log


class FragmentExtendedInformation : Fragment() {
    private var _binding: FragmentExtendedInformationBinding? = null
    private val binding get() = _binding!!
    private val receivedId = arguments?.getInt("recipe_id")
    private val informationViewModel: ExtendedInformationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentExtendedInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setFabVisibility(VISIBLE)

        informationViewModel.foods.observe(viewLifecycleOwner){

            recipe -> Glide.with(this).load(recipe.image).into(binding.imageView)
            binding.readyInMinutes.text =  recipe.readyInMinutes.toString()
            binding.veganTag.text =  recipe.vegan.toString()
            binding.servings.text =  recipe.servings.toString()
            binding.glutenTag.text =  recipe.glutenFree.toString()
            binding.dairyTag.text =  recipe.dairyFree.toString()
            binding.recipeName.text =  recipe.title.toString()
            Log.d("SPAS", " BINDING AS WE SPEAK")

        }
        binding.apply {
            readyInMinutes
            imageView
            veganTag
            servings
            glutenTag
            dairyTag
            ingredientsScroll
            recipeScroll
            recipeName

            starLayout.apply {
                setRoundedCorners(120F)
            }
        }
        (activity as? MainActivity)?.setFabVisibility(View.GONE)

    }



}