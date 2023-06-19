package com.example.projectapp.data

import org.junit.Test
import junit.framework.TestCase.assertEquals
import com.example.projectapp.R
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class GetBackgroundTest {

    @Test
    fun getSunriseBackground(){
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

        val currentTime = LocalTime.parse("11:30", timeFormatter).atDate(LocalDate.now())
        val sunrise = LocalTime.parse("11:29", timeFormatter).atDate(LocalDate.now())
        val sunset = LocalTime.parse("17:45", timeFormatter).atDate(LocalDate.now())
        val zenith = LocalTime.parse("14:30", timeFormatter).atDate(LocalDate.now())


        val result = R.drawable.sunrise

        val actualResult = getBackground(currentTime, sunrise, sunset, zenith)

        assertEquals(result, actualResult)
    }


    @Test
    fun getZenithBackground(){
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

        val currentTime = LocalTime.parse("15:30", timeFormatter).atDate(LocalDate.now())
        val sunrise = LocalTime.parse("11:29", timeFormatter).atDate(LocalDate.now())
        val sunset = LocalTime.parse("17:45", timeFormatter).atDate(LocalDate.now())
        val zenith = LocalTime.parse("14:30", timeFormatter).atDate(LocalDate.now())


        val result = R.drawable.zenith

        val actualResult = getBackground(currentTime, sunrise, sunset, zenith)

        assertEquals(result, actualResult)
    }

    @Test
    fun getSunsetBackground(){
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

        val currentTime = LocalTime.parse("18:30", timeFormatter).atDate(LocalDate.now())
        val sunrise = LocalTime.parse("11:29", timeFormatter).atDate(LocalDate.now())
        val sunset = LocalTime.parse("17:45", timeFormatter).atDate(LocalDate.now())
        val zenith = LocalTime.parse("14:30", timeFormatter).atDate(LocalDate.now())


        val result = R.drawable.sunset

        val actualResult = getBackground(currentTime, sunrise, sunset, zenith)

        assertEquals(result, actualResult)
    }

    @Test
    fun getNullBackground(){
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

        val currentTime = LocalTime.parse("11:30", timeFormatter).atDate(LocalDate.now())
        val sunrise = LocalTime.parse("11:30", timeFormatter).atDate(LocalDate.now())
        val sunset = LocalTime.parse("11:30", timeFormatter).atDate(LocalDate.now())
        val zenith = LocalTime.parse("11:30", timeFormatter).atDate(LocalDate.now())


        val result = null

        val actualResult = getBackground(currentTime, sunrise, sunset, zenith)

        assertEquals(result, actualResult)
    }




}