package com.example.weatherapp

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.view.adapters.MainDailyListAdapter
import com.example.weatherapp.view.adapters.MainHourlyListAdapter
import com.google.android.gms.location.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder

const val GEO_LOCATION_REQUEST_CODE_SUCCESS = 1
const val TAG = "GEO_TEST"
class MainActivity : AppCompatActivity() {

    private val geoService by lazy { LocationServices.getFusedLocationProviderClient(this)}
    private val locationRequest by lazy { initLocationRequest() }

    private lateinit var mLocation: Location

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        checkPermission()

        initViews();

        binding.mainHourlyList.apply {
            adapter = MainHourlyListAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        binding.mainDailyList.apply {
            adapter = MainDailyListAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }

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
                Log.d(TAG, "onLocationResult: lat: ${location.latitude}; lon: ${location.longitude};")
            }
        }
    }

    // ------------------- initial activity code ------------------
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d(TAG, "onRequestPermissionsResult: $requestCode")
        //------------launch MainActivity
    }

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            MaterialAlertDialogBuilder(this)
                .setTitle("This app needs your geo data")
                .setMessage("Please allow access to your geo data to continue work with app")
                .setPositiveButton("Ok"){ _,_ ->
                    ActivityCompat.requestPermissions(this,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        GEO_LOCATION_REQUEST_CODE_SUCCESS
                    )
                    ActivityCompat.requestPermissions(this,
                        arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
                        GEO_LOCATION_REQUEST_CODE_SUCCESS
                    )
                }
                .setNegativeButton("Cancel") { dialog,_ -> dialog.dismiss()}
                .create()
                .show()
        }
    }

    // ------------------- location code ------------------

}