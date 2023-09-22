package com.example.myapplication.ui.extendedInformation

import android.content.SharedPreferences
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
import android.content.Context;
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.adapters.ItemClickListener
import com.example.myapplication.data.FrontFood
import kotlin.properties.Delegates

class FragmentExtendedInformation : Fragment() {
    private var _binding: FragmentExtendedInformationBinding? = null
    private val binding get() = _binding!!
    val sharedPreferencesFavorites:SharedPreferences = requireActivity().getSharedPreferences("favoritePref", Context.MODE_PRIVATE)
    val sharedPreferencesEditor = sharedPreferencesFavorites.edit()


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

//        binding.favoriteButton.setOnClickListener {
//            sharedPreferencesEditor.apply {
//                putString("recipe_id",bundleOf("recipe_id" to recipe.id).toString())
//            }
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setFabVisibility(VISIBLE)

        var thrownId:Int
        informationViewModel.foods.observe(viewLifecycleOwner){

            recipe ->
            Glide.with(this).load(recipe.image).into(binding.imageView)

            binding.readyInMinutes.text =  recipe.readyInMinutes.toString()
            binding.veganTag.text =  recipe.vegan.toString()
            binding.servings.text =  recipe.servings.toString()
            binding.glutenTag.text =  recipe.glutenFree.toString()
            binding.dairyTag.text =  recipe.dairyFree.toString()
            binding.recipeName.text =  recipe.title.toString()
//            binding.ingredients.text = recipe.ingredients.toString()
//            binding.instructions.text = recipe.analyzedInstructions.toString()
            Log.d("BIND", " BINDING AS WE SPEAK")
            thrownId = recipe.id
            binding.favoriteButton.apply {
                setOnClickListener{
                    sharedPreferencesEditor.apply{

                    }
                    Toast.makeText(context, "You have clicked the thing", Toast.LENGTH_SHORT).show()
                informationViewModel.addExtendedFoodId(thrownId)
                Log.d("ADD", thrownId.toString()) }

            }
            binding.starLayout.apply {
                setRoundedCorners(120F)
            }
//            Log.d("ADD", thrownId.toString())
        }



        (activity as? MainActivity)?.setFabVisibility(View.GONE)

    }



}