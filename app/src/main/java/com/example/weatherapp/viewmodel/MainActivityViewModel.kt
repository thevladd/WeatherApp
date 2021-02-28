package com.example.weatherapp.viewmodel

import androidx.lifecycle.*
import com.example.weatherapp.model.WeatherList
import com.example.weatherapp.repository.MainActivityRepository

class MainActivityViewModel : ViewModel() {

    var listWeather: MutableLiveData<WeatherList>? = null

    fun getWeather() : MutableLiveData<WeatherList>? {
        listWeather = MainActivityRepository.getApiCall()
        return listWeather
    }

}