package com.example.weatherapp.ui.model

import com.example.weatherapp.data.dto.Clouds
import com.example.weatherapp.data.dto.Coord
import com.example.weatherapp.data.dto.Main
import com.example.weatherapp.data.dto.Rain
import com.example.weatherapp.data.dto.Sys
import com.example.weatherapp.data.dto.Weather
import com.example.weatherapp.data.dto.Wind

data class WeatherModel(
    val id: Int,
    val name: String,
    val cod: Int,
    val main: Main,
    val coord: Coord,
    val sys: Sys,
    val clouds: Clouds,
    val wind: Wind,
    val rain: Rain?,
    val base: String,
    val dt: Int,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>
)