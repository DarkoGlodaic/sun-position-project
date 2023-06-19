package com.example.projectapp.viewmodels

import com.example.projectapp.data.ApiData
import com.example.projectapp.data.ApiGet
import com.example.projectapp.data.DataSource
import com.example.projectapp.data.calculateSunriseSunset
import com.example.projectapp.model.ForecastApi
import com.example.projectapp.model.SunriseApi
import java.time.LocalTime

class DataRepository() {

    val data = DataSource()

    suspend fun getData(lat: Double, lon: Double, apiDate: String, year: Int, month: Int, day: Int): ApiData {
        //Gjør et sunrise api kall
        val dataSunrise = data.getInstance().create(ApiGet::class.java).getSunrise(lat, lon, apiDate)
        //Gjør et forecast api kall
        val dataForecast = data.getInstance().create(ApiGet::class.java).getForecast(lat, lon)
        //Kalkulerer
        val dataCalculation: List<String> = calculateSunriseSunset(lat, lon, year, month, day)
        //Oppretter en objekt som holder på informasjonen fra kallet.
        return lagDataObjekt(dataSunrise, dataForecast, dataCalculation)
    }

    //Trekker ifra offset slik at tiden blir riktig.
    //Lager Api objekt
    private fun lagDataObjekt(sunrise: SunriseApi, forecastApi: ForecastApi, calculation: List<String>): ApiData {
        val liste = listOf(
            sunrise.properties.sunrise.time,
            sunrise.properties.sunset.time,
            sunrise.properties.solarnoon.time
        )
        val listeObjekt = mutableListOf<String>()

        for (x in liste) {
            val time1 = LocalTime.parse(x.substring(11, 16))
            val time2 = LocalTime.parse(x.substring(17, 22))
            listeObjekt += (time1.plusHours(time2.hour.toLong()).plusMinutes(time2.minute.toLong()).toString())
        }

        return ApiData(
            listeObjekt[0], listeObjekt[1], listeObjekt[2],
            calculation[0], calculation[2], calculation[1], forecastApi
        )
    }
}
