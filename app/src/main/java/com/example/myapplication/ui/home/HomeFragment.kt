package com.example.myapplication.ui.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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
        binding.apply {

            homeViewModel.trivia.observe(viewLifecycleOwner){
                Log.d("TRIVIA RECIEVED", it)
                binding.trivia.text = "$it :O"
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
            nutrientsSearch.apply {

                setExplicableRoundedCorners(0f, 0f, 0f, 80f)
                setOnClickListener {
                    findNavController().navigate(R.id.fragmentFavoritesInflater)


                }
            }
            weeklyRecommended.apply {
                setExplicableRoundedCorners(0f, 0f, 80f, 0f)
                setOnClickListener {
                    findNavController().navigate(R.id.action_information_icon_to_fragmentWeekly) }

            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}