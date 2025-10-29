package com.example.weatherapp.ui.viewmodel

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun formatUnixTimestamp(timestamp: Int, formatter: DateTimeFormatter): String {
    return Instant.ofEpochSecond(timestamp.toLong())
        .atZone(ZoneId.systemDefault())
        .toLocalTime()
        .format(formatter)
}