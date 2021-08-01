package com.sachin.weatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sachin.weatherapp.R
import com.sachin.weatherapp.databinding.SingleWeatherLayoutBinding
import com.sachin.weatherapp.models.Temperature

class WeatherAdapter(
    private val weatherList: List<Temperature>
) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>()  {
    class ViewHolder(private val binding: SingleWeatherLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(temperature: Temperature){
            binding.apply {
                tvTime.text = temperature.dt_txt.substringAfter(" ")
                if(temperature.weather[0].main == "Clouds") {
                    ivWeather.setImageResource(R.drawable.ic_cloud)
                } else{
                    ivWeather.setImageResource(R.drawable.ic_sun)
                }
                tvWeather.text = temperature.weather[0].description
                tvTemp.text = temperature.main.temp.toString() + "C"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = SingleWeatherLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val temp = weatherList[position]
        holder.bind(temp)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }


}