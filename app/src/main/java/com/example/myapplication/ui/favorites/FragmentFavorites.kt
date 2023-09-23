package com.example.myapplication.ui.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
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
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setFabVisibility(View.GONE)
        val foodDatabase = MainActivity.getDatabaseInstance()
        val foodBase = foodDatabase.dao()
        favoritesViewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]
        val itemAdapter = OptionRecyclerAdapter().apply {
            itemClickListener = object : ItemClickListener<FrontFood> {
                override fun onItemClicked(item: FrontFood, itemPosition: Int) {
                    Log.d("SENDING ID", item.id.toString())
                    findNavController().navigate(R.id.action_fragmentFavorites_to_blahblah, bundleOf("recipe_id" to item.id))
                }
            }
        }
        var shit = favoritesViewModel.getDatabaseEntries()
        Log.d("SHIT", shit.toString())



//            binding.apply {
//                itemAdapter.updateItems(favoritesViewModel.foodList)
//            }
        }
//        lifecycleScope.launch(Dispatchers.IO) {
//            val foodRoomInfoList = foodBase.getAllFoods()
//            val frontFoodList = foodRoomInfoList.mapNotNull { reverseRoomAdapter.adapt(it) }
//
//            Log.d("FOOD REBASED", frontFoodList.toString())
//
//        }

    }

}