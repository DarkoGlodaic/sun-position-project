package com.example.projectapp.data

import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.Calendar
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

class SunCalculation {

    // TODO: ADD LOCATION FROM DEVICE IN TO calculateSunrise()
    // Then also deal with if location passed is Null
}

fun calculateSunrise(): List<String> {
    val osloLatitude = 59.9139 // Oslo latitude
    val osloLongitude = 10.7522 // Oslo longitude
    val date = LocalDate.of(2023, 4, 28)
    return calculateSunriseSunset(
        osloLatitude,
        osloLongitude,
        date.year,
        date.monthValue,
        date.dayOfMonth
    )
}

fun calculateFractionalYear(dayOfYear: Int, leapYear: Boolean): Double {
    var days = 365
    // If leap year we add a day
    if (leapYear) days++
    // We skip adding hour-12 /24 to the parentheses as given noon it amounts to 0
    return 2 * PI / days * (dayOfYear - 1)
}

fun calculateLeapYear(year: Int): Boolean {
    return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
}

fun calculateDayOfYear(year: Int, month: Int, day: Int): Int {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month - 1)
    calendar.set(Calendar.DAY_OF_MONTH, day)
    return calendar.get(Calendar.DAY_OF_YEAR)
}

// Returns Equation of Time in minutes
fun calculateEquationOfTime(fractionalYear: Double): Double {
    return 229.18 * (0.000075 + 0.001868 * cos(fractionalYear) - 0.032077 * sin(fractionalYear) -
            0.014615 * cos(2 * fractionalYear) - 0.040849 * sin(2 * fractionalYear))
}

// Returns Solar Declination in Radians
fun calculateSolarDeclination(fractionalYear: Double): Double {
    val decl = 0.006918 - 0.399912 * cos(fractionalYear) + 0.070257 * sin(fractionalYear) - 0.006758 * cos(2 * fractionalYear)
    + 0.000907 * sin(2 * fractionalYear) - 0.002697 * cos(3 * fractionalYear) + 0.00148 * sin(3 * fractionalYear)
    return decl
}

fun calculateHourAngle(latitude: Double, decl: Double) : Double {
    // Hour angle for sunrise (sunset is the negative value of the hour angle)
    return Math.toDegrees(acos(cos(Math.toRadians(90.833)) /(cos(Math.toRadians(latitude)) * cos(decl)) - tan(Math.toRadians(latitude)) * tan(decl)))
}

fun calculateSunriseSunset(latitude: Double, longitude: Double, year: Int, month: Int, day: Int): List<String> {
    val dayOfYear = calculateDayOfYear(year, month, day)
    val fractionalYear = calculateFractionalYear(dayOfYear, calculateLeapYear(year))
    val eqTime = calculateEquationOfTime(fractionalYear)
    val decl = calculateSolarDeclination(fractionalYear)
    val hourAngle = calculateHourAngle(latitude, decl)

    // The UTC offset of the local machine in minutes
    val timeOffset = ZonedDateTime.now().offset.totalSeconds/60

    val sunriseTime = 720 - 4 * (longitude + hourAngle) - eqTime + timeOffset
    val noonTime = 720 - 4 * longitude - eqTime + timeOffset
    val sunsetTime = 720 - 4 * (longitude - hourAngle) - eqTime + timeOffset

    val formattedSunrise = String.format("%02d:%02d", (sunriseTime / 60).toInt(), ((sunriseTime / 60 - (sunriseTime / 60).toInt()) * 60).toInt())
    val formattedNoon = String.format("%02d:%02d", (noonTime / 60).toInt(), ((noonTime / 60 - (noonTime / 60).toInt()) * 60).toInt())
    val formattedSunset = String.format("%02d:%02d", (sunsetTime / 60).toInt(), ((sunsetTime / 60 - (sunsetTime / 60).toInt()) * 60).toInt())

    return listOf(formattedSunrise, formattedNoon, formattedSunset)
}
