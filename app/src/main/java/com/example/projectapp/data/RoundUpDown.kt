package com.example.projectapp.data

import kotlin.math.ceil
import kotlin.math.floor



fun roundUpDown(num : Double): String{


    val roundedNum = if (num - num.toInt() >= 0.5) {
        ceil(num).toInt()
    } else {
        floor(num).toInt()
    }
    return roundedNum.toString()
}
