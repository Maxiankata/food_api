package com.example.myapplication.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.setExplicableRoundedCorners

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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
        binding.apply {
            imageView.setOnClickListener {
                unlockDrawer()
            }
            homeViewModel.trivia.observe(viewLifecycleOwner){
                binding.trivia.text = "$it :O"
            }
            homeViewModel.recipe.observe(viewLifecycleOwner){
                Glide.with(imageScroller)
                    .load(it.image)
                    .into(imageScroller)
                var item = it.id
                imageScroller.setOnClickListener {
                        findNavController().navigate(R.id.action_Home_to_blahblah, bundleOf("recipe_id" to item))
                }

            }
            imageScroller.apply {
                setExplicableRoundedCorners(120F, 10F, 120F, 10F)

            }
            ingredients.apply {
                setExplicableRoundedCorners(80f, 80f, 0f, 0f)
                setOnClickListener {
                        findNavController().navigate(R.id.searchInflater)
                }
            }

            weeklyRecommended.apply {
                setExplicableRoundedCorners(0f, 0f, 80f, 80f)
                setOnClickListener {
                    findNavController().navigate(R.id.action_information_icon_to_fragmentWeekly) }

            }
        }
    }
    fun lockDrawer() {
        (activity as? MainActivity)?.lockDrawer()
    }
    fun unlockDrawer() {
        (activity as? MainActivity)?.unlockDrawer()
    }
    override fun onDestroyView() {
        lockDrawer()
        super.onDestroyView()
        _binding = null
    }
}