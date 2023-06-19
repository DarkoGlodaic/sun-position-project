package com.example.projectapp.data
import org.junit.Test
import junit.framework.TestCase.assertEquals
import com.example.projectapp.R
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class SunCalculationTest{



    @Test
    fun testCalculateLeapYear() {
        // Test a non-leap year
        assertEquals(false, calculateLeapYear(2022))

        // Test a leap year
        assertEquals(true, calculateLeapYear(2024))
    }

    @Test
    fun testCalculateDayOfYear() {
        assertEquals(118, calculateDayOfYear(2023, 4, 28))
        assertEquals(1, calculateDayOfYear(2023, 1, 1))
        assertEquals(365, calculateDayOfYear(2023, 12, 31))
    }

    @Test
    fun testCalculateSunriseSunset() {
        val sunriseSunset = calculateSunriseSunset(59.9139, 10.7522, 2023, 4, 28)
        assertEquals("05:23", sunriseSunset[0])
        assertEquals("13:14", sunriseSunset[1])
        assertEquals("21:04", sunriseSunset[2])
    }


}