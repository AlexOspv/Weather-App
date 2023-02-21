package com.example.weatherapp

import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.business.model.DailyWeatherModel
import com.example.weatherapp.business.model.HourlyWeatherModel
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.presenters.MainPresenter
import com.example.weatherapp.view.MainView
import com.example.weatherapp.view.adapters.MainDailyListAdapter
import com.example.weatherapp.view.adapters.MainHourlyListAdapter
import com.google.android.gms.location.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

const val TAG = "GEO_TEST"
class MainActivity : MvpAppCompatActivity(), MainView {

    private val mainPresenter by moxyPresenter { MainPresenter() }

    private val geoService by lazy { LocationServices.getFusedLocationProviderClient(this)}
    private val locationRequest by lazy { initLocationRequest() }

    private lateinit var mLocation: Location

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initViews()

        binding.mainHourlyList.apply {
            adapter = MainHourlyListAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        binding.mainDailyList.apply {
            adapter = MainDailyListAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        mainPresenter.enable()

        geoService.requestLocationUpdates(locationRequest, geoCallback, null)

    }

    private fun initViews() {

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

    // -------------------- Moxy code -----------------------------

    override fun displayLocation(data: String) {
        binding.mainCityNameTextView.text = data
    }

    override fun displayCurrentData(data: WeatherData) {
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

    override fun displayHourlyData(data: List<HourlyWeatherModel>) {
        (binding.mainHourlyList.adapter as MainHourlyListAdapter).updateData(data)
    }

    override fun displayDailyData(data: List<DailyWeatherModel>) {
        (binding.mainDailyList.adapter as MainDailyListAdapter).updateData(data)
    }

    override fun displayError(error: Throwable) {

    }

    override fun setLoading(flag: Boolean) {

    }

    // -------------------- Moxy code -----------------------------

    // ------------------- location code ------------------

    private fun initLocationRequest(): LocationRequest {
        return LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000)
            .setMinUpdateIntervalMillis(5000)
            .build()
    }

    private val geoCallback = object : LocationCallback() {
        override fun onLocationResult(geo: LocationResult) {
            Log.d(TAG, "onLocationResult: ${geo.locations.size}")
            for (location in geo.locations) {
                mLocation = location
                mainPresenter.refresh(mLocation.latitude.toString(), mLocation.longitude.toString())
                Log.d(TAG, "onLocationResult: lat: ${location.latitude}; lon: ${location.longitude};")
            }
        }
    }

    // ------------------- location code ------------------

}