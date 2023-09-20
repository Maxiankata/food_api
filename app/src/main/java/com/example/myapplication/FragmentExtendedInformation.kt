package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentExtendedInformationBinding
import com.example.myapplication.databinding.FragmentIngredientsBinding


class FragmentExtendedInformation : Fragment() {
    private var _binding: FragmentExtendedInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentExtendedInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setFabVisibility(VISIBLE)
        binding.apply {
            readyInMinutes
            imageView
            starLayout.apply {
                setRoundedCorners(120F)
            }
        }
    }



}