package com.example.weatherapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.viewmodel.WeatherUiState
import com.example.weatherapp.ui.viewmodel.WeatherViewModel

@Composable
fun WeatherView(viewModel: WeatherViewModel){
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.weather___home_2),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            SearchBar(onSearch = { city ->
                viewModel.fetchWeather(city)
            })

            val uiState by viewModel.uiState.collectAsState()

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                item {
                    when (val state = uiState) {
                        is WeatherUiState.Initial -> InitialState()
                        is WeatherUiState.Loading -> LoadingState()
                        is WeatherUiState.Success -> SuccessState(data = state.data)
                        is WeatherUiState.Error -> ErrorState(message = state.message)
                    }
                }
            }
        }
    }
}