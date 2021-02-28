package com.example.weatherapp.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.HoursRowBinding
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.service.ConvertService

class HoursAdapter(
    private val lst: List<Int>,
    private val weatherList: List<WeatherData> = arrayListOf()
) : RecyclerView.Adapter<HoursAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            DataBindingUtil.inflate<HoursRowBinding>
                (
                LayoutInflater.from(parent.context),
                R.layout.hours_row,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.recyclerviewBinding.time = ConvertService.convertedTime(weatherList[position].dtTxt)
        holder.recyclerviewBinding.image=weatherList[position].weather[0].icon
    }

    override fun getItemCount(): Int {
        return lst.size
    }

    inner class MyViewHolder(val recyclerviewBinding: HoursRowBinding) :
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