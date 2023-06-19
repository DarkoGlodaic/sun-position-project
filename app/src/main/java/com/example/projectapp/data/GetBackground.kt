package com.example.projectapp.data

import com.example.projectapp.R
import java.time.LocalDateTime

fun getBackground(currentTime: LocalDateTime, sunriseTime: LocalDateTime, sunsetTime: LocalDateTime, zenithTime: LocalDateTime): Int? {
    return when {
        currentTime.isAfter(sunriseTime) && currentTime.isBefore(zenithTime) -> R.drawable.sunrise
        currentTime.isAfter(zenithTime) && currentTime.isBefore(sunsetTime) -> R.drawable.zenith
        currentTime.isAfter(sunsetTime) || currentTime.isBefore(sunriseTime) -> R.drawable.sunset
        else -> null
    }
}