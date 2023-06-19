package com.example.projectapp.data

import com.example.projectapp.model.ForecastApi

//Holder på tid
data class ApiData(
    val apiSunrise:String,
    val apiSunset:String,
    val apiSolarnoon:String,
    val calculationSunrise:String,
    val calculationSunset:String,
    val calculationSolarnoon:String,
    //Vær data
    val weatherData:ForecastApi

)