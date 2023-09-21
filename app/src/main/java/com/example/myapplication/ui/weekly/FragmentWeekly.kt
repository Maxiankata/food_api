package com.example.myapplication.ui.weekly

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.adapters.ItemClickListener
import com.example.myapplication.adapters.OptionRecyclerAdapter
import com.example.myapplication.data.FrontFood
import com.example.myapplication.databinding.FragmentWeeklyBinding
import com.example.myapplication.ui.Ingredients.IngredientsViewModel


class FragmentWeekly : Fragment() {

    private var _binding: FragmentWeeklyBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeeklyBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weeklyViewModel = ViewModelProvider(this)[ViewModelWeekly::class.java]

        val itemAdapter = OptionRecyclerAdapter().apply {
            itemClickListener = object : ItemClickListener<FrontFood> {
                override fun onItemClicked(item: FrontFood, itemPosition: Int) {
                    Log.d("ID SENT", item.id.toString())
                    findNavController().navigate(R.id.action_fragmentWeekly_to_blahblah, bundleOf("recipe_id" to item.id))
                }
            }
        }
        weeklyViewModel._foods.observe(viewLifecycleOwner){

        }

        binding.apply {
            itemRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = itemAdapter
            }
        }
        weeklyViewModel._foods.observe(viewLifecycleOwner){
            binding.apply {
                itemAdapter.updateItems(it)
            }
        }
    }
}