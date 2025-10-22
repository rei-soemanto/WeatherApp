package com.example.weatherapp.data.dto

data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_max: Double,
    val temp_min: Double,
    val pressure: Int,
    val humidity: Int,
    val sea_level: Int,
    val grnd_level: Int
)