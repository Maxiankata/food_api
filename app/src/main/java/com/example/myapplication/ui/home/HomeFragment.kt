package com.example.myapplication.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.setExplicableRoundedCorners

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        binding.apply {



                imageScroller.apply {
                    setExplicableRoundedCorners(120F, 10F, 120F, 10F)
                }
                ingredients.apply {
                    setExplicableRoundedCorners(80f, 0f, 0f, 0f)
                    setOnClickListener {
                        view?.let {
                            Navigation.findNavController(it).navigate(R.id.IngredientSearch)
                        }
                    }
                }

                dishSearch.apply {
                    setExplicableRoundedCorners(0f, 80f, 0f, 0f)

                    setOnClickListener {
                        view?.let {
                            Navigation.findNavController(it).navigate(R.id.fragmentDishName)
                        }
                    }
                }
                nutrientsSearch.apply {

                    setExplicableRoundedCorners(0f, 0f, 0f, 80f)
                    setOnClickListener {
                        view?.let {
                            Navigation.findNavController(it).navigate(R.id.fragmentNutrientSearch)
                        }

                    }
                }
                weeklyRecommended.apply {
                    setExplicableRoundedCorners(0f, 0f, 80f, 0f)
                    setOnClickListener {
                        view?.let { Navigation.findNavController(it).navigate(R.id.fragmentDishName) }
                    }
                }


            return root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}