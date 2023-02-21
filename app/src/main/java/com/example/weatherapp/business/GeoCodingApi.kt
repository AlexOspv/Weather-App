package com.example.weatherapp.business

import com.example.weatherapp.business.model.GeoCodeModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoCodingApi {

    @GET("geo/1.0/reverse?")
    fun getCityByCoord(
        @Query("lat") lat : String,
        @Query("lon") lon : String,
        @Query("limit") limit : String = "10",
        @Query("appid") id : String = "d096bb953b31f6bd5e50c5163d34be68"
    ) : Observable<List<GeoCodeModel>>

}