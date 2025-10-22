package com.example.weatherapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.ui.model.WeatherModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface WeatherUiState {
    object Initial : WeatherUiState
    object Loading : WeatherUiState
    data class Success(val data: WeatherModel) : WeatherUiState
    data class Error(val message: String) : WeatherUiState
}

class WeatherViewModel(
    private val repository: WeatherRepository = WeatherRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Initial)
    val uiState: StateFlow<WeatherUiState> = _uiState

    fun fetchWeather(city: String) {
        if (city.isBlank()) {
            _uiState.value = WeatherUiState.Error("City name cannot be empty")
            return
        }

        _uiState.value = WeatherUiState.Loading
        viewModelScope.launch {
            try {
                val response = repository.getWeather(city)
                if (response.isSuccessful && response.body() != null) {
                    _uiState.value = WeatherUiState.Success(response.body()!!)
                }
                else {
                    val errorMsg = "HTTP ${response.code()} ${response.message()}"
                    _uiState.value = WeatherUiState.Error(errorMsg)
                }
            } catch (e: IOException) {
                _uiState.value = WeatherUiState.Error("Network error: ${e.message}")
            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error("Oops! Something went wrong: ${e.message}")
            }
        }
    }
}