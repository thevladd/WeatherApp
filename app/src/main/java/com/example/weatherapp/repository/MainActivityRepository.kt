package com.example.weatherapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.WeatherList
import com.example.weatherapp.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {

    val weatherData = MutableLiveData<WeatherList>()
    val KEY = "8a23954340e98a6f53c7b01ba3c12c59"
    val city = "578072"

    fun getApiCall(): MutableLiveData<WeatherList> {
        ApiClient.apiService.getWeather(city , KEY).enqueue(object : Callback<WeatherList> {
            override fun onFailure(call: Call<WeatherList>, t: Throwable) {
                weatherData.postValue(null)
                Log.e("error", t.localizedMessage!!)
            }

            override fun onResponse(
                call: Call<WeatherList>,
                response: Response<WeatherList>
            ) {
                weatherData.postValue(response.body())


            }
        })
        return weatherData
    }
}