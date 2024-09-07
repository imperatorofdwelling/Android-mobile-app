package com.imperatorofdwelling.android.presentation.ui.city_selection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.imperatorofdwelling.android.databinding.CityElementBinding
import com.imperatorofdwelling.android.databinding.CityElementSelectedBinding
import com.imperatorofdwelling.android.domain.models.City

class CitySelectionAdapter : ListAdapter<City , RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<City>(){
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem == newItem
    }
}) {



    companion object {
        const val CITY_NOT_SELECTED = 1
        const val CITY_SELECTED = 2
        const val MAX_POOL_SIZE = 15
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == CITY_SELECTED) {
            val binding =
                CityElementSelectedBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return CitySelectedViewHolder(binding)
        } else {
            val binding = CityElementBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return CityNotSelectedViewHolder(binding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isSelected) {
            CITY_SELECTED
        } else {
            CITY_NOT_SELECTED
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val city = getItem(position)
        when (holder) {
            is CityNotSelectedViewHolder -> holder.bind(city)
            is CitySelectedViewHolder -> holder.bind(city)
        }
    }

    inner class CityNotSelectedViewHolder(private val binding: CityElementBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(city: City) {
            binding.cityName.text = city.name
        }
    }

    inner class CitySelectedViewHolder(private val binding: CityElementSelectedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(city: City) {
            binding.cityName.text = city.name
        }
    }

}