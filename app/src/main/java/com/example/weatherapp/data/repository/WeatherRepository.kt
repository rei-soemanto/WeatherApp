package com.example.weatherapp.data.repository

import com.example.weatherapp.data.service.RetrofitInstance
import com.example.weatherapp.data.service.WeatherApiService
import com.example.weatherapp.ui.model.WeatherModel
import retrofit2.Response

class WeatherRepository(
    private val apiService: WeatherApiService = RetrofitInstance.api
) {
    private val apiKey = "b363791588fe2e52acc1fdce20a54104"

    suspend fun getWeather(city: String): Response<WeatherModel> {
        return apiService.getCurrentWeather(city = city, apiKey = apiKey)
    }
}