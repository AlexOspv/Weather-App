package com.example.weatherapp.business

import com.example.weatherapp.business.model.WeatherDataModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/3.0/onecall?")
    fun getWeatherForecast(
        @Query("lat") lat : String,
        @Query("lon") lon : String,
        @Query("exclude") exclude : String = "minutely,alerts",
        @Query("appid") apiid : String = "d096bb953b31f6bd5e50c5163d34be68",
        @Query("lang") lang : String = "en"
    ) : Observable<WeatherDataModel>
}