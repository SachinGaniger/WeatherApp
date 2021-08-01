package com.sachin.weatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sachin.weatherapp.R
import com.sachin.weatherapp.databinding.FragmentCityBinding
import com.sachin.weatherapp.databinding.FragmentDetailBinding
import com.sachin.weatherapp.ui.adapters.CitiesAdapter
import com.sachin.weatherapp.ui.adapters.WeatherAdapter
import com.sachin.weatherapp.ui.viewModels.ViewModel
import com.sachin.weatherapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    lateinit var binding: FragmentDetailBinding
    private var weatherAdapter: WeatherAdapter? = null
    val viewModel : ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val city: String = arguments?.getString("city") as String

        viewModel.getForecast(city)

        viewModel.weather.observe(viewLifecycleOwner, Observer {weatherResponse ->



            when(weatherResponse){
                is Resource.Success ->{
                    hideProgressbar()
                    weatherResponse.data?.let {response->
                        weatherAdapter = WeatherAdapter(response.list)

                        binding.apply {

                            tvCity.text = city
                            rvTodayForecast.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
                            rvTodayForecast.adapter = weatherAdapter
                            response.list[0].also {
                                tvTemp.text = it.main.temp.toString() + "C"
                                tvWeather.text = it.weather[0].description
                                tvTime.text = it.dt_txt
                            }

                        }

                    }

                }

                is Resource.Loading ->{
                    showProgressBar()
                }

                is Resource.Error ->{
                    hideProgressbar()
                    Toast.makeText(activity, weatherResponse.message, Toast.LENGTH_SHORT).show()
                }
            }

        })



        return binding.root
    }

    private fun showProgressBar() {
        binding.progressbar.visibility = View.VISIBLE
        binding.progressbar.visibility = View.VISIBLE
    }

    private fun hideProgressbar() {
        binding.progressbar.visibility = View.GONE
        binding.progressbar.visibility = View.GONE
    }

}