package com.example.projectapp.model



data class SunriseApi(
    val type:String,
    val copyright:String,
    val licenseURL:String,
    val geometry: Geometry2,
    val properties: Properties2
)

data class Geometry2(
    val coordinates:List<Double>
)

data class When(
    val interval:List<String>
)

data class Properties2(
    val body:String,
    val solarmidnight: Solarmidnight,
    val solarnoon: Solarnoon,
    val sunrise: Sunrise,
    val sunset: Sunset
)

data class Solarmidnight(
    val disc_centre_elevation:Double,
    val time:String,
    val visible:Boolean
)

data class Solarnoon(
    val disc_centre_elevation:Double,
    val time:String,
    val visible:Boolean
)

data class Sunrise(
    val azimuth:Double,
    val time:String
)

data class Sunset(
    val azimuth:Double,
    val time:String,

    )