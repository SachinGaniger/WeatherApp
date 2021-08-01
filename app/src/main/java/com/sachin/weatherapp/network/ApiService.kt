package com.sachin.weatherapp.network

import com.sachin.weatherapp.models.WeatherResponse
import com.sachin.weatherapp.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") country: String,
        @Query("appid") api_key: String = API_KEY,
        @Query("units") units: String = "metric",
    ) : Response<WeatherResponse>

}