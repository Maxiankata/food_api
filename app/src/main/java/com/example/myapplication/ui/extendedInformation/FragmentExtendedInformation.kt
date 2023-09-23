package com.example.myapplication.ui.extendedInformation

import InstructionViewPagerAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.FragmentExtendedInformationBinding
import com.example.myapplication.room.FullInformationToRoomAdapter
import com.example.myapplication.room.RoomToFullFrontFoodAdapter
import com.example.myapplication.setRoundedCorners
import com.example.myapplication.ui.IngredientsFragment.RecipeContainerAdapter
import com.example.myapplication.ui.ingredientsInformation.IngredientsViewPagerAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FragmentExtendedInformation : Fragment() {
    private var _binding: FragmentExtendedInformationBinding? = null
    private val binding get() = _binding!!
    lateinit var recipeContainerAdapter: RecipeContainerAdapter
    lateinit var instructionViewPagerAdapter: InstructionViewPagerAdapter
    lateinit var ingredientsViewPagerAdapter:IngredientsViewPagerAdapter
    lateinit var roomAdapter: FullInformationToRoomAdapter
    lateinit var otherRoomAdapter: RoomToFullFrontFoodAdapter



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
        recipeContainerAdapter = RecipeContainerAdapter()

        instructionViewPagerAdapter = InstructionViewPagerAdapter()
        ingredientsViewPagerAdapter = IngredientsViewPagerAdapter()

        otherRoomAdapter = RoomToFullFrontFoodAdapter()

        val foodDatabase = MainActivity.getDatabaseInstance()
        val foodBase = foodDatabase.dao()

        val roomToFullFrontFoodAdapter = RoomToFullFrontFoodAdapter()
        roomAdapter = FullInformationToRoomAdapter()

        binding.ingredientsViewPager.adapter = ingredientsViewPagerAdapter

        binding.instructionsViewPager.adapter = instructionViewPagerAdapter
        informationViewModel.foods.observe(viewLifecycleOwner) { recipe ->
            Glide.with(this)
                .load(recipe.image)
                .into(binding.imageView)
                .view.setRoundedCorners(30F)
            binding.apply {
                readyInMinutes.text = recipe.readyInMinutes.toString()
                veganTag.text = recipe.vegan.toString()
                servings.text = recipe.servings.toString()
                glutenTag.text = recipe.glutenFree.toString()
                dairyTag.text = recipe.dairyFree.toString()
                recipeName.text = recipe.title.toString()

                ingredientsViewPager.setRoundedCorners(20F)
                instructionsViewPager.setRoundedCorners(20F)

                favoriteButton.setOnClickListener {
                    starLayoutChecked.visibility = VISIBLE
                    starLayout.visibility = GONE
                    val foodRoomInfo = roomAdapter.adapt(recipe)
                    foodRoomInfo?.let {
                        lifecycleScope.launch(Dispatchers.IO) {
                            Log.d("FOODBASED", foodRoomInfo.toString())
                            foodBase.saveFood(it)
                        }
                    }
                }
                favoritedButton.setOnClickListener{
                    starLayout.visibility = VISIBLE
                    starLayoutChecked.visibility = GONE
                    val foodRoomInfo = roomAdapter.adapt(recipe)
                    foodRoomInfo?.let {
                        lifecycleScope.launch(Dispatchers.IO) {
                            Log.d("FOODBASED", foodRoomInfo.toString())
                            foodBase.deleteFood(it)
                        }
                    }
                }

            }


            ingredientsViewPagerAdapter.updateItems(recipe.ingredients)
            Log.d("INGREDIENTS", recipe.ingredients.toString())
            instructionViewPagerAdapter.updateItems(recipe.analyzedInstructions)
            Log.d("INSTRUCTIONS", recipe.analyzedInstructions.toString())

        }


        binding.starLayoutChecked.visibility = GONE
        binding.apply {
            starLayout.setRoundedCorners(120F)
            favoriteButton.apply {
                setOnClickListener{
                }
            }
            starLayoutChecked.setRoundedCorners(120F)
            favoritedButton.apply {

                setOnClickListener{

                }
            }
            (activity as? MainActivity)?.setFabVisibility(View.GONE)

        }


    }
}