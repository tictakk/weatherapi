package com.example.nooruapp.data

data class Location(
    val name: String
)

data class Condition(
    val text: String,
    val icon: String,
    val code: Int
)

data class Current(
    val condition: Condition? = null,
    val temp_f: Float? = 0.0f,
    val weatherCon: String? = "",
    val humidity: Int? = 0,
    val uv: Float? = 0.0f,
    val feelslike_f: Float? = 0.0f
)

data class WeatherData(
    val location: Location,
    val current: Current
)