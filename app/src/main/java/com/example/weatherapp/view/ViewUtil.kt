package com.example.weatherapp.view

import com.example.weatherapp.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

const val DAY_FULL_MONTH_NAME = "dd MMMM"
const val DAY_WEEK_NAME_LONG = "dd EEEE"
const val HOUR_DOUBLE_DOT_MINUTE = "HH:mm"

fun Long.toDateFormatOf(format: String): String{
    val cal = Calendar.getInstance()
    val timeZone = cal.timeZone
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    sdf.timeZone = timeZone
    return sdf.format(Date(this*1000))
}

fun Double.toDegree() = (this - 273.15).roundToInt().toString()

fun Double.toPercentString(extraPart: String = "") = (this*100).roundToInt().toString() + extraPart

fun String.provideIcon() = when(this) {
    "01n", "01d" -> R.drawable.ic_01d
    "02n", "02d" -> R.drawable.ic_02d
    "03n", "03d" -> R.drawable.ic_03d
    "04n", "04d" -> R.drawable.ic_04d
    "09n", "09d" -> R.drawable.ic_09d
    "10n", "10d" -> R.drawable.ic_10d
    "11n", "11d" -> R.drawable.ic_11d
    "13n", "13d" -> R.drawable.ic_13d
    "50n", "50d" -> R.drawable.ic_50d
    else -> R.drawable.ic_error
}

fun String.provideMainIcon() = when(this) {
    "01n", "01d" -> R.mipmap.sunny_3x
    "02n", "02d" -> R.mipmap.sun_clouds_3x
    "03n", "03d" -> R.mipmap.cloudy_sun_3x
    "04n", "04d" -> R.mipmap.clouds_3x
    "09n", "09d" -> R.mipmap.rain_3x
    "10n", "10d" -> R.mipmap.sun_rain_3x
    "11n", "11d" -> R.mipmap.thunder_3x
    "13n", "13d" -> R.mipmap.snow_3x
    "50n", "50d" -> R.mipmap.fog_3x
    else -> R.drawable.ic_error
}