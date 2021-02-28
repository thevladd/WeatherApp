package com.example.weatherapp.view

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.MyHandler
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ForecastRowBinding
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.service.ConvertService


class ForecastAdapter(

    private val lst: List<Int>,
    private val weatherList: List<List<WeatherData>> = arrayListOf(),
    private var recycler_main1: RecyclerView,
    private  var binding: com.example.weatherapp.databinding.ActivityMainBinding
) : RecyclerView.Adapter<ForecastAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            DataBindingUtil.inflate<ForecastRowBinding>
                (
                LayoutInflater.from(parent.context),
                R.layout.forecast_row,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.recyclerviewBinding.week = ConvertService.convertedWeek(weatherList[position][0].dtTxt)
        holder.recyclerviewBinding.image=weatherList[position][3].weather[0].icon
        holder.recyclerviewBinding.date = ConvertService.convertedDate(weatherList[position][0].dtTxt)
        holder.recyclerviewBinding.temp = ConvertService.convertedKelvinToCelsius(weatherList[position][0].main.temp)
        val myHandler = MyHandler(weatherList, recycler_main1, position, binding)
        holder.recyclerviewBinding.handler = myHandler
    }

    override fun getItemCount(): Int {
        return lst.size
    }

    inner class MyViewHolder(val recyclerviewBinding: ForecastRowBinding) :
        RecyclerView.ViewHolder(recyclerviewBinding.root) {

    }

    companion object {
        @JvmStatic
        @BindingAdapter(
            "srcUrl",
            "circleCrop",
            "placeholder",
            requireAll = false
        )
        fun ImageView.bindSrcUrl(
            url: String,
            circleCrop: Boolean = false,
            placeholder: Drawable?
        ) = Glide.with(this).load("http://openweathermap.org/img/wn/"+url+".png").let { request ->

            if (circleCrop) {
                request.circleCrop()
            }

            if (placeholder != null) {
                request.placeholder(placeholder)
            }

            request.into(this)
        }
    }

}
