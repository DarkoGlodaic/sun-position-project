package com.example.projectapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectapp.R
import com.example.projectapp.data.roundUpDown
import com.example.projectapp.ui.components.LazyRowItem
import com.example.projectapp.viewmodels.DataViewModel
import com.example.projectapp.viewmodels.DataViewState
import com.example.projectapp.data.getBackground
import com.example.projectapp.model.ForecastApi
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun SunScreen(
    sunrise:String,
    solarnoon:String,
    sunset:String,
    weatherData: ForecastApi?
) {
    val weatherInfo = weatherData?.properties?.timeseries
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    var background by remember { mutableStateOf(R.drawable.zenith) }

    LaunchedEffect(Unit) {
        while (true) {
            val currentTime = LocalDateTime.now()
            val sunrise = LocalTime.parse(sunrise, timeFormatter).atDate(LocalDate.now())
            val sunset = LocalTime.parse(sunset, timeFormatter).atDate(LocalDate.now())
            val zenith = LocalTime.parse(solarnoon, timeFormatter).atDate(LocalDate.now())
            val backgroundId = getBackground(currentTime, sunrise, sunset, zenith)
            if (backgroundId != null) {
                background = backgroundId
            }
            delay(60000) // 1 min delay
        }
    }

    @Suppress("UnusedMaterialScaffoldPaddingParameter")
    Scaffold{
        Box(
           modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            LazyColumn( modifier = Modifier
                .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally) {
                item {
                    Column(modifier = Modifier
                        .padding(1.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                        Row {
                            Spacer(modifier = Modifier.width(28.dp))
                            Text(
                                text = if(weatherInfo == null){
                                    " "
                                }
                                        else{
                                weatherInfo[2].data.instant.details.air_temperature.let { it1 ->
                                    roundUpDown(
                                        it1
                                    )
                                } + "°"},
                                fontSize = 90.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Text(
                            text = "Oslo",
                            fontSize = 50.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Spacer(modifier = Modifier.height(60.dp))
                }
                // Sunrise
                item {
                    ElevatedCard(modifier = Modifier
                        .padding(6.dp)
                        .fillMaxSize()
                    ) {
                        Column(modifier = Modifier
                            .fillMaxWidth(),
                        )
                        {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier .fillMaxWidth()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_sunrise),
                                    contentDescription = "Sunrise icon",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .offset(x = 0.dp, y = (-2).dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Sunrise",
                                    fontSize = 25.sp,
                                    modifier = Modifier.padding(vertical = 8.dp),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(
                                    text = sunrise,
                                    fontSize = 20.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.width(60.dp))
                            }
                        }
                    }
                }

                // Zenith
                item {
                    ElevatedCard(modifier = Modifier
                        .padding(6.dp)
                        .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_zenith),
                                    contentDescription = "Zenith icon",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .offset(x = 0.dp, y = (0).dp)
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(
                                    text = "Zenith",
                                    fontSize = 25.sp,
                                    modifier = Modifier.padding(vertical = 8.dp),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.width(21.dp))
                                Text(
                                    text = solarnoon,
                                    fontSize = 20.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                            }
                        }
                    }
                }

                // Sunset
                item {
                    ElevatedCard(modifier = Modifier
                        .padding(6.dp)
                        .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                        )
                        {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_sunset),
                                    contentDescription = "Sunset icon",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .offset(x = 0.dp, y = (-2).dp)
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(
                                    text = "Sunset",
                                    fontSize = 25.sp,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )
                                Spacer(modifier = Modifier.width(17.dp))
                                Text(
                                    text = sunset,
                                    fontSize = 20.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                            }
                        }
                    }
                }
                // Weather
                item {
                    ElevatedCard(modifier = Modifier
                        .padding(6.dp)
                        .fillMaxSize()
                    ) {
                        LazyRow(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,

                        ){
                            if (weatherInfo != null) {
                                items(weatherInfo.take(25)) {timeData ->
                                    LazyRowItem(timeData)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SunScreenState(viewModel: DataViewModel) {
    val state by viewModel.state.collectAsState()
    val setting by viewModel.settingCalculation.collectAsState()
    //Følger med og oppdaterer skjermen utifra state
    when (state) {
        DataViewState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
            CircularProgressIndicator()}
        }
        //Hvis state er Loaded så viser skjermen SunScreen
        is DataViewState.Loaded -> {

            when(setting) {
                true -> {
                    //Data variabelen holder på tidene
                    val data = (state as DataViewState.Loaded).data
                    SunScreen(data.calculationSunrise, data.calculationSolarnoon,
                        data.calculationSunset, data.weatherData)

                }

                false ->{
                    val data = (state as DataViewState.Loaded).data
                    SunScreen(
                        sunrise = data.apiSunrise, solarnoon = data.apiSolarnoon ,
                        sunset = data.apiSunset , weatherData = data.weatherData)
                }
            }
        }
        is DataViewState.Error -> {

            val data = (state as DataViewState.Error).data

            SunScreen(data[0], data[1], data[2], null)
        }

    }
}
