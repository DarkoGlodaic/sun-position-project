package com.example.projectapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectapp.R
import com.example.projectapp.data.ApiData
import com.example.projectapp.viewmodels.DataViewModel
import com.example.projectapp.viewmodels.DataViewState
import com.example.projectapp.data.getBackground
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Composable
fun Table(api: ApiData) {

    val column0Weight = .3f
    val column1Weight = .3f
    val column2Weight = .4f

    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    var background by remember { mutableStateOf(R.drawable.zenith) }

    val data = listOf(
        listOf("Sunrise", api.calculationSunrise, api.apiSunrise),
        listOf("Sunset", api.calculationSunset, api.apiSunset),
        listOf("Zenith", api.calculationSolarnoon, api.apiSolarnoon)
    )

    LaunchedEffect(Unit) {
        while (true) {
            val currentTime = LocalDateTime.now()
            val sunrise = LocalTime.parse(api.apiSunrise, timeFormatter).atDate(LocalDate.now())
            val sunset = LocalTime.parse(api.apiSunset, timeFormatter).atDate(LocalDate.now())
            val zenith = LocalTime.parse(api.apiSolarnoon, timeFormatter).atDate(LocalDate.now())
            val backgroundId = getBackground(currentTime, sunrise, sunset, zenith)
            if (backgroundId != null) {
                background = backgroundId
            }
            delay(60000) // wait for a minute before checking the time again
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

    Spacer(modifier = Modifier.height(150.dp))

    Column {


        LazyColumn(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {


            item {
                Row(Modifier.background(androidx.compose.material3.MaterialTheme.colorScheme.surface)) {
                    TableCell(text = "", weight = column2Weight)
                    TableCell(text = "App", weight = column0Weight)
                    TableCell(text = "Api", weight = column1Weight)
                }
            }


            items(data) {

                Row(Modifier .fillMaxWidth() .background(androidx.compose.material3.MaterialTheme.colorScheme.surface)) {
                    TableCell(text = it[0], weight = column2Weight)
                    TableCell(text = it[1], weight = column0Weight)
                    TableCell(text = it[2], weight = column1Weight)
                }
            }
        }

    }

    }
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    //hva:String,
    //tid:String
) {
    androidx.compose.material3.Text(
        text = text,
        Modifier
            .border(1.dp, Color.LightGray)
            .weight(weight)
            .padding(8.dp),
        fontSize = 20.sp
    )
}


@Composable
fun TableScreen(viewModel: DataViewModel) {
    val state by viewModel.state.collectAsState()


    when (state) {
        DataViewState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                CircularProgressIndicator()}
        }
        is DataViewState.Loaded -> {
            val data = (state as DataViewState.Loaded).data
            Column {
                Table(data)
            }

        }
        is DataViewState.Error -> {
            val message = (state as DataViewState.Error).message
            Text("Error: $message")
        }

    }
}