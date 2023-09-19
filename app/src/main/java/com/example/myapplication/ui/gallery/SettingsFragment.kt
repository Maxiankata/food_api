package com.example.myapplication.ui.gallery

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val galleryViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

    _binding = FragmentSettingsBinding.inflate(inflater, container, false)
    val root: View = binding.root

      binding.apply {
          themeLayout.setOnClickListener {
              languageLayout.setBackgroundColor(getColor(requireContext(), R.color.blue_dark))
              themeLayout.setBackgroundColor(getColor(requireContext(), R.color.blue_dark))
              settingsLayout.setBackgroundColor(getColor(requireContext(), R.color.blue_faded))
              languageIcon.setColorFilter(R.color.blue_light)
              languageText.setTextColor(getColor(requireContext(), R.color.blue_light))
              sunIcon.visibility = GONE
              moonIcon.apply {
                  languageIcon.setColorFilter(R.color.blue_light)
                  visibility = VISIBLE }
              themeText.setTextColor(getColor(requireContext(), R.color.blue_light))


              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
              val window: Window? = activity?.window
              window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
              window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.blue_faded)

          } }

      }

  
    return root
  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}