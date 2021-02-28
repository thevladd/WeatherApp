package com.example.weatherapp.retrofit

import com.example.weatherapp.model.WeatherList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("data/2.5/forecast?")
    fun getWeather(@Query("id") city: String, @Query("appid") app_id: String): Call<WeatherList>

}