package com.example.weatherapp.service

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ConvertService {

    companion object {
        fun convertedTime(time: String): String {
            val convertedTime = time.split(" ")[1].split(":")
            return convertedTime[0]
        }

        fun convertedDate(stringDate: String): String {
            val date = SimpleDateFormat("yyyy-MM-dd").parse(stringDate)
            val format = "dd.MM.yyyy"
            val simpleDateFormat = SimpleDateFormat(format)
            return simpleDateFormat.format(date)
        }

        fun convertedWeek(stringDate: String): String {
            val date=stringDate.split(" ")
            val format1 = SimpleDateFormat("yyyy-MM-dd")
            val dt1: Date = format1.parse(date[0])
            val format2: DateFormat = SimpleDateFormat("EEEE")
            val finalDay: String = format2.format(dt1)
            return finalDay
        }

        fun convertedKelvinToCelsius(kelvin: Double): String {
            return (kelvin - 273.15).toInt().toString()+"℃"
        }
    }
}