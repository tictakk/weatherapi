package com.example.nooruapp.data

import dagger.Provides
import okhttp3.Response
import javax.inject.Inject

class WeatherService @Inject constructor(): ApiService {
    override suspend fun getWeatherData(key: String, q: String): retrofit2.Response<WeatherData> {
        TODO("Not yet implemented") }

}