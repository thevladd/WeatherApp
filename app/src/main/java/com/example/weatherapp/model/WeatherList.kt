package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Days(
    var day: List<List<WeatherData>> = arrayListOf()
)

data class WeatherList(
    @SerializedName("city")
    var city: City,
    @SerializedName("list")
    var list: List<WeatherData>
): Serializable

data class WeatherData(
    @SerializedName("dt_txt")
    var dtTxt: String,
    @SerializedName("main")
    var main: Main,
    @SerializedName("weather")
    var weather: List<Weather>
): Serializable

data class Weather(
    @SerializedName("description")
    var description: String,
    @SerializedName("icon")
    var icon: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("main")
    var main: String
): Serializable

data class Main(
    @SerializedName("grnd_level")
    var grndLevel: Int,
    @SerializedName("temp")
    var temp: Double,
    @SerializedName("temp_kf")
    var tempKf: Double,
    @SerializedName("temp_max")
    var tempMax: Double,
    @SerializedName("temp_min")
    var tempMin: Double

): Serializable

data class City(
    @SerializedName("country")
    var country: String
): Serializable