package com.imperatorofdwelling.android.presentation.ui.city_selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.imperatorofdwelling.android.databinding.FragmentCitySelectionBinding

class CitySelectionFragment: Fragment() {
    private lateinit var binding: FragmentCitySelectionBinding
    private val viewModel: CitySelectionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCitySelectionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}