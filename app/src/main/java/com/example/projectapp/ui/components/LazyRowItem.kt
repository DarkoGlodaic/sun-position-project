package com.example.projectapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectapp.data.roundUpDown
import com.example.projectapp.model.TimeSeries
import java.time.LocalTime


@Composable
fun LazyRowItem(data : TimeSeries){
    val currentTime = LocalTime.now()
    val dataString = data.time.substring(11, 13)
    val time = if (currentTime.hour.toString() == dataString) "Now" else dataString

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text( // time
            text = time,
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.onSurface
        )
        Text( //temperature
            text = roundUpDown(data.data.instant.details.air_temperature) + "°",
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
