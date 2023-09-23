package com.example.myapplication.ui.extendedInformation

import InstructionViewPagerAdapter
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
import com.example.myapplication.ui.Ingredients.IngredientsViewPagerAdapter


class FragmentExtendedInformation : Fragment() {
    private var _binding: FragmentExtendedInformationBinding? = null
    private val binding get() = _binding!!
    lateinit var ingredientsViewPagerAdapter: IngredientsViewPagerAdapter
    lateinit var instructionViewPagerAdapter: InstructionViewPagerAdapter

    private val informationViewModel: ExtendedInformationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        also { Log.d("ID RECIEVED", arguments?.getInt("recipe_id").toString()) }
        informationViewModel.fetchFood(arguments?.getInt("recipe_id")!!)
        _binding = FragmentExtendedInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setFabVisibility(VISIBLE)
        ingredientsViewPagerAdapter = IngredientsViewPagerAdapter()
        instructionViewPagerAdapter = InstructionViewPagerAdapter()

        binding.ingredientsViewPager.adapter = ingredientsViewPagerAdapter
        binding.instructionsViewPager.adapter = instructionViewPagerAdapter
        informationViewModel.foods.observe(viewLifecycleOwner) { recipe ->
            Glide.with(this).load(recipe.image).into(binding.imageView)
            binding.apply {
                readyInMinutes.text = recipe.readyInMinutes.toString()
                veganTag.text = recipe.vegan.toString()
                servings.text = recipe.servings.toString()
                glutenTag.text = recipe.glutenFree.toString()
                dairyTag.text = recipe.dairyFree.toString()
                recipeName.text = recipe.title.toString()
            }

//            ingredientsViewPagerAdapter.updateItems(recipe.ingredients)
            instructionViewPagerAdapter.updateItems(recipe.instructions)
            Log.d("INSTRUCTIONS", recipe.instructions.toString())

        }
        binding.apply {
            starLayout.apply {
                setRoundedCorners(120F)

            }
            (activity as? MainActivity)?.setFabVisibility(View.GONE)

        }


    }
}