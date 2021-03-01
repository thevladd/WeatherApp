package com.example.weatherapp.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.Days
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.model.WeatherList
import com.example.weatherapp.service.ConvertService
import com.example.weatherapp.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var context: Context
    private var adapter1: ForecastAdapter? = null
    private var adapter2: HoursAdapter? = null
    var weatherList: List<List<WeatherData>> = arrayListOf()
    //lateinit var days: Days
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var binding : com.example.weatherapp.databinding.ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =    DataBindingUtil.setContentView(this, R.layout.activity_main)
        context = this@MainActivity
        recycler_main1.layoutManager = LinearLayoutManager(this@MainActivity)
        recycler_main2.layoutManager=LinearLayoutManager(
            this@MainActivity,
            RecyclerView.HORIZONTAL,
            false
        )
        if(isConnected) {
            getData()
        }else
            Toast.makeText(context, R.string.err_connection, Toast.LENGTH_SHORT).show()


    }
    private fun daysByHours(listWeather: WeatherList){
        var i: Int=0

        if(!(ConvertService.convertedTime(listWeather.list[0].dtTxt)).equals("00:00:00"))
            i=ConvertService.convertedTime(listWeather.list[0].dtTxt).split(":")[0].toInt()/3
        weatherList= listOf(listWeather.list.take(8), *(listWeather.list.drop(8 - i).chunked(8)).toTypedArray())
        //days.day=listOf(listWeather.list.take(8), *(listWeather.list.drop(8 - i).chunked(8)).toTypedArray())
    }

    fun getData(){
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel.getWeather()?.observe(this, Observer { listWeather ->
            daysByHours(listWeather)
            binding.daytimeTemperature =
                ConvertService.convertedKelvinToCelsius(weatherList[0][0].main.temp)
            binding.desc = weatherList[0][0].weather[0].description
            adapter1 = ForecastAdapter(
                listOf(1, 2, 3, 4, 5),
                weatherList,
                recycler_main2,
                binding
            )
            recycler_main1.adapter = adapter1
            adapter1!!.notifyDataSetChanged()

            adapter2 = HoursAdapter(
                listOf(1, 2, 3, 4, 5, 6, 7, 8),
                weatherList[0]
            )
            recycler_main2.adapter = adapter2
            adapter2!!.notifyDataSetChanged()
        })
    }

    fun onClickRefresh(view: View) {
        if(isConnected) {
            getData()
        }else
            Toast.makeText(context, R.string.err_connection, Toast.LENGTH_SHORT).show()
    }

    val Context.isConnected: Boolean
        get() {
            return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo?.isConnected == true
        }
}