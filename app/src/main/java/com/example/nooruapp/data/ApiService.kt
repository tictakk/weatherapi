package com.example.nooruapp.data

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/v1/current.json")
    suspend fun getWeatherData(@Query("key")key: String, @Query("q")q: String): Response<WeatherData>
}