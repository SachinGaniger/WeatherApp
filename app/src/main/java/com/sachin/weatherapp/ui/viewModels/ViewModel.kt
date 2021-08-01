package com.sachin.weatherapp.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachin.weatherapp.models.WeatherResponse
import com.sachin.weatherapp.repository.Repository
import com.sachin.weatherapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    var weather: MutableLiveData<Resource<WeatherResponse>> = MutableLiveData()

    public fun getForecast(city: String) = viewModelScope
        .launch(Dispatchers.IO) {
            weather.postValue(Resource.Loading())
            val weatherValue = handleWeather(repository.getWeatherForecast(city))
            weather.postValue(weatherValue)
        }

    private fun handleWeather(response: Response<WeatherResponse>): Resource<WeatherResponse> {

        if (response.isSuccessful){
            response.body()?.let {weatherResponse ->
                return Resource.Success(weatherResponse)
            }
        }
        return  Resource.Error(response.message())
    }

}