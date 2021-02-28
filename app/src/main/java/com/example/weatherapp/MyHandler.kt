package com.example.weatherapp

import android.annotation.SuppressLint
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.service.ConvertService
import com.example.weatherapp.view.HoursAdapter
import com.example.weatherapp.view.MainActivity
import com.example.weatherapp.viewmodel.MainActivityViewModel

class MyHandler(
                val weatherList: List<List<WeatherData>> = arrayListOf(),
                val recycler_main2: RecyclerView,
                val position: Int,
                val binding: com.example.weatherapp.databinding.ActivityMainBinding
) {
    private var adapter1: HoursAdapter? = null
    val numbers1: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8)
    val list = weatherList
    @SuppressLint("ResourceAsColor")
    fun onClick(view: View?) {
        binding.daytimeTemperature= ConvertService.convertedKelvinToCelsius(weatherList[position][0].main.temp)
        binding.desc= weatherList[position][0].weather[0].description
        adapter1 = HoursAdapter(
            listOf(1, 2, 3, 4, 5, 6, 7, 8),
            weatherList[position]
        )
        recycler_main2.adapter = adapter1
        adapter1!!.notifyDataSetChanged()
    }
}