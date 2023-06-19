package com.example.projectapp.model



data class ForecastApi(
    val type: String,
    val geometry: Geometry1,
    val properties: Properties1
)


data class Geometry1(
    val type: String,
    val coordinates: List<Double>
)

data class Properties1(
    val meta: Meta,
    val timeseries: List<TimeSeries>
)

data class Meta(
    val updated_at: String,
    val units: Units
)

data class Units(
    val air_pressure_at_sea_level: String,
    val air_temperature: String,
    val cloud_area_fraction: String,
    val precipitation_amount: String,
    val relative_humidity: String,
    val wind_from_direction: String,
    val wind_speed: String
)

data class TimeSeries(
    val time: String,
    val data: Data
)

data class Data(
    val instant: Instant,
    val next_12_hours: Next12Hours,
    val next_1_hours: Next1Hour,
    val next_6_hours: Next6Hours
)

data class Instant(
    val details: Details
)

data class Details(
    val air_pressure_at_sea_level: Double,
    val air_temperature: Double,
    val cloud_area_fraction: Double,
    val relative_humidity: Double,
    val wind_from_direction: Double,
    val wind_speed: Double
)

data class Next12Hours(
    val summary: Summary
)

data class Summary(
    val symbol_code: String
)

data class Next1Hour(
    val summary: Summary,
    val details: HourDetails
)

data class HourDetails(
    val precipitation_amount: Double
)

data class Next6Hours(
    val summary: Summary,
    val details: HourDetails
)