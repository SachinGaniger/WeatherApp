package com.sachin.weatherapp.repository

import com.sachin.weatherapp.network.ApiService
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {

    suspend fun getWeatherForecast(city: String) = apiService.getForecast(city)

}