package com.sachin.weatherapp.ui.adapters

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sachin.weatherapp.databinding.SingleCityLayoutBinding

class CitiesAdapter(
    private val cities: List<String>,
    private val clickListener: (String) -> Unit
) : RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: SingleCityLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindCity(city: String) {
            binding.tvCity.text = city
            binding.clCity.setOnClickListener {
                clickListener(city)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = SingleCityLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city: String = cities[position]
        holder.bindCity(city)
    }



    override fun getItemCount(): Int {
        return cities.size
    }
}