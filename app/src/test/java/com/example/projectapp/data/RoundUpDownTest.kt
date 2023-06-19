package com.example.projectapp.data

import junit.framework.TestCase.assertEquals
import org.junit.Test

class RoundUpDownTest {

    @Test
    fun roundUp(){
        val num = 5.6
        val result = "6"

        val actualResult = roundUpDown(num)

        assertEquals(result, actualResult)
    }

    @Test
    fun roundDown(){
        val num = 5.4
        val result = "5"

        val actualResult = roundUpDown(num)

        assertEquals(result, actualResult)
    }

    @Test
    fun noRoundUpOrDown(){
        val num = 5.0
        val result = "5"

        val actualResult = roundUpDown(num)

        assertEquals(result, actualResult)
    }
}