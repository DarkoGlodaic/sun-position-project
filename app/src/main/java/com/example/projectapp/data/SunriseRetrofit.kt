package com.example.projectapp.data



import com.example.projectapp.model.ForecastApi
import com.example.projectapp.model.SunriseApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URL = "https://gw-uio.intark.uh-it.no/in2000/weatherapi/"

class DataSource {
    private val baseUrl = BASE_URL

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}


interface ApiGet{

    @Headers("X-Gravitee-Api-Key: f0e1067d-cf7e-4479-9a18-674c1797b0ec")
    @GET("sunrise/3.0/sun")
    suspend fun getSunrise(@Query("lat") lat: Double,
                           @Query("lon") lon: Double,
                           @Query("date") date: String,
                           @Query("offset") offset: String = "+01:00"): SunriseApi
    @Headers("X-Gravitee-Api-Key: f0e1067d-cf7e-4479-9a18-674c1797b0ec")
    @GET("locationforecast/2.0/compact?")
    suspend fun getForecast(@Query("lat") lat: Double,
                            @Query("lon") lon:Double): ForecastApi
}



