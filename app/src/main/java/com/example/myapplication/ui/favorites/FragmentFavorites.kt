package com.example.myapplication.ui.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.adapters.ItemClickListener
import com.example.myapplication.adapters.OptionRecyclerAdapter
import com.example.myapplication.data.FrontFood
import com.example.myapplication.databinding.FragmentFavoritesBinding
import com.example.myapplication.databinding.FragmentRecipeNameBinding
import com.example.myapplication.room.RoomToFullFrontFoodAdapter
import com.example.myapplication.setRoundedCorners
import com.example.myapplication.ui.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log


class FragmentFavorites : Fragment() {

    lateinit var reverseRoomAdapter: RoomToFullFrontFoodAdapter
    lateinit var favoritesViewModel: FavoritesViewModel
    private var _binding: FragmentFavoritesBinding? = null


    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoritesViewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]
        favoritesViewModel.getDatabaseEntries()
        (activity as? MainActivity)?.setFabVisibility(View.GONE)
        val foodDatabase = MainActivity.getDatabaseInstance()
        val foodBase = foodDatabase.dao()

        val itemAdapter = OptionRecyclerAdapter().apply {
            itemClickListener = object : ItemClickListener<FrontFood> {
                override fun onItemClicked(item: FrontFood, itemPosition: Int) {
                    Log.d("SENDING ID", item.id.toString())
                    findNavController().navigate(R.id.action_fragmentFavorites_to_blahblah, bundleOf("recipe_id" to item.id))
                }
            }
        }
//        binding.itemRecyclerView.setRoundedCorners(20F)
        binding.itemRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.itemRecyclerView.adapter = itemAdapter


        reverseRoomAdapter = RoomToFullFrontFoodAdapter()

//        var existScope : List<FrontFood>
//        lifecycleScope.launch(Dispatchers.IO) {
//            var roomInfoList = foodBase.getAllFoods().mapNotNull { reverseRoomAdapter.adapt(it) }
//            existScope = roomInfoList
//            Log.d("MAYBE", existScope.toString())
//            binding.apply {
//                itemAdapter.updateItems(existScope)
//            }
//        }
        Log.d("SHIT", favoritesViewModel.foodListLiveData.toString())

        favoritesViewModel.foodListLiveData.observe(viewLifecycleOwner, Observer { foodList ->
            Log.d("SHIT", foodList.toString())
            Log.d("SHIT", "Food list size: ${foodList.size}")

            itemAdapter.updateItems(foodList)
        })


//        Log.d("DATABASE RECIEVED", favoritesViewModel.foodList.toString())

//        favoritesViewModel.foods.observe(viewLifecycleOwner){
//            Log.d("SHIT", favoritesViewModel.foods.toString())
//
//            binding.apply {
//                itemAdapter.updateItems(it)
//            }
//        }


        }
//        lifecycleScope.launch(Dispatchers.IO) {
//            val foodRoomInfoList = foodBase.getAllFoods()
//            val frontFoodList = foodRoomInfoList.mapNotNull { reverseRoomAdapter.adapt(it) }
//
//            Log.d("FOOD REBASED", frontFoodList.toString())
//
//        }

    }

