package com.sachin.weatherapp.models

data class WeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Temperature>,
    val message: Int
)