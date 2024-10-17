//package com.imperatorofdwelling.android.presentation.ui.city_selection
//
//import android.os.Bundle
//import android.text.Editable
//import android.text.TextWatcher
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.recyclerview.widget.DefaultItemAnimator
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.imperatorofdwelling.android.databinding.FragmentCitySelectionBinding
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class CitySelectionFragment : Fragment() {
//
//    companion object {
//        @JvmStatic
//        fun newInstance() = CitySelectionFragment()
//    }
//
//    private lateinit var binding: FragmentCitySelectionBinding
//    private val viewModel: CitySelectionViewModel by viewModels()
//    private lateinit var citySelectionAdapter: CitySelectionAdapter
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentCitySelectionBinding.inflate(inflater)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initRecyclerView()
//        initObserver()
//        addTextListener()
//    }
//
//    private fun addTextListener() {
//        binding.searchBar.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                val text = binding.searchBar.text.toString()
//                viewModel.searchCity(text)
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//            }
//        })
//    }
//
//    private fun initObserver() {
//        viewModel.searchCityResult.observe(viewLifecycleOwner) { newData ->
//            citySelectionAdapter.submitList(newData)
//        }
//        viewModel.defaultCity.observe(viewLifecycleOwner) { newData ->
//            citySelectionAdapter.defaultCity = newData
//        }
//    }
//
//
//    private fun initRecyclerView() {
//        citySelectionAdapter = CitySelectionAdapter().apply {
//            onItemClickListener = { city ->
//                viewModel.setDefaultCity(city)
//            }
//        }
//        with(binding.recyclerView) {
//            adapter = citySelectionAdapter
//            layoutManager = LinearLayoutManager(requireActivity())
//            itemAnimator = DefaultItemAnimator()
//            recycledViewPool.setMaxRecycledViews(
//                CitySelectionAdapter.CITY_SELECTED,
//                CitySelectionAdapter.MAX_POOL_SIZE
//            )
//            recycledViewPool.setMaxRecycledViews(
//                CitySelectionAdapter.CITY_NOT_SELECTED,
//                CitySelectionAdapter.MAX_POOL_SIZE
//            )
//        }
//    }
//}