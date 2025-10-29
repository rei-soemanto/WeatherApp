package com.example.weatherapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weatherapp.R
import com.example.weatherapp.ui.model.WeatherModel
import com.example.weatherapp.ui.viewmodel.formatUnixTimestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun SuccessState(data: WeatherModel) {
    val dateFormatter = DateTimeFormatter.ofPattern("MMMM dd", Locale.ENGLISH)
    val timeFormatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH)
    val now = LocalDateTime.now()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "LocationIcon",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = data.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Text(
            text = now.format(dateFormatter),
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Updated as of ${now.format(timeFormatter)}",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(100.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://openweathermap.org/img/wn/${data.weather.first().icon}@4x.png")
                        .crossfade(true)
                        .build(),
                    contentDescription = data.weather.first().description,
                    modifier = Modifier.size(100.dp)
                )

                Text(
                    text = data.weather.first().main,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "${data.main.temp.roundToInt()}°C",
                    fontSize = 56.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            }
            val pandaImage = when (data.weather.first().main.lowercase(Locale.ROOT)) {
                "clear" -> R.drawable.blue_and_black_bold_typography_quote_poster_3
                "rain" -> R.drawable.blue_and_black_bold_typography_quote_poster_2
                "clouds" -> R.drawable.blue_and_black_bold_typography_quote_poster
                else -> R.drawable.blue_and_black_bold_typography_quote_poster_2
            }
            Image(
                painter = painterResource(id = pandaImage),
                contentDescription = "Panda ${data.weather.first().main}",
                modifier = Modifier.size(150.dp)
            )
        }

        Spacer(modifier = Modifier.height(100.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WeatherDetailItem(
                iconId = R.drawable.icon_humidity,
                label = "HUMIDITY",
                value = "${data.main.humidity}%"
            )
            WeatherDetailItem(
                iconId = R.drawable.icon_wind,
                label = "WIND",
                value = "${data.wind.speed.roundToInt()} km/h"
            )
            WeatherDetailItem(
                iconId = R.drawable.icon_feels_like,
                label = "FEELS LIKE",
                value = "${data.main.feels_like.roundToInt()}°"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WeatherDetailItem(
                iconId = R.drawable.vector_2,
                label = "RAINFALL",
                value = "${data.rain?.oneHour ?: 0.0} mm"
            )
            WeatherDetailItem(
                iconId = R.drawable.devices,
                label = "PRESSURE",
                value = "${data.main.pressure} hPa"
            )
            WeatherDetailItem(
                iconId = R.drawable.cloud,
                label = "CLOUDS",
                value = "${data.clouds.all}%"
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            SunTimeItem(
                iconId = R.drawable.vector,
                label = "SUNRISE",
                time = formatUnixTimestamp(data.sys.sunrise, timeFormatter)
            )

            Spacer(modifier = Modifier.width(72.dp))

            SunTimeItem(
                iconId = R.drawable.vector_21png,
                label = "SUNSET",
                time = formatUnixTimestamp(data.sys.sunset, timeFormatter)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}