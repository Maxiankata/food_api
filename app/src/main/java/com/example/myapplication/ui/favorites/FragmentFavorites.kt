package com.example.myapplication.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.adapters.ItemClickListener
import com.example.myapplication.adapters.OptionRecyclerAdapter
import com.example.myapplication.data.FrontFood
import com.example.myapplication.databinding.FragmentFavoritesBinding
import com.example.myapplication.room.RoomToFullFrontFoodAdapter


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
        val itemAdapter = OptionRecyclerAdapter().apply {
            itemClickListener = object : ItemClickListener<FrontFood> {
                override fun onItemClicked(item: FrontFood, itemPosition: Int) {
                    findNavController().navigate(
                        R.id.action_fragmentFavorites_to_blahblah,
                        bundleOf("recipe_id" to item.id)
                    )
                }
            }
        }
        binding.itemRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.itemRecyclerView.adapter = itemAdapter
        reverseRoomAdapter = RoomToFullFrontFoodAdapter()
        favoritesViewModel.foodListLiveData.observe(viewLifecycleOwner, Observer { foodList ->
            itemAdapter.updateItems(foodList)
        })
    }
}

