package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.mainCityNameTextView.text = "Moscow"
        binding.mainDateTextView.text = "16 february"
        binding.mainWeatherConditionIcon.setImageResource(R.drawable.ic_sunny_24)
        binding.mainWeatherConditionTextView.text = "Sunny"
        binding.mainTemp.text = "25\u00B0"
        binding.mainTempMinTextView.text = "19"
        binding.mainTempMaxTextView.text = "28"
        binding.mainTempAvgTextView.text = "24"
        binding.mainWeatherImageView.setImageResource(R.mipmap.clowd_3x)
        binding.mainPressureTextView.text = "1023 hPa"
        binding.mainHumidityTextView.text = "91%"
        binding.mainWindSpeedTextView.text = "4 m/s"
        binding.mainSunriseTextView.text = "05:24"
        binding.mainSunsetTextView.text = "19:34"

    }
}