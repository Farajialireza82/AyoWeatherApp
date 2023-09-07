package com.biblioklept.weatherapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->

        val hour = data.get(0).exactDeviceTime.format(
            DateTimeFormatter.ofPattern("HH")
        ).toInt()

        val listState = rememberLazyListState()

        val coroutinesScope = rememberCoroutineScope()


        Column (
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "Today",
                fontSize = 20.sp,
                color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(state = listState, content = {
                items(data){weatherData ->
                    HourlyWeatherDisplay(weatherData = weatherData,
                        modifier = Modifier
                            .height(100.dp)
                            .padding(horizontal = 16.dp))
                }
            })

            LaunchedEffect(hour){
                coroutinesScope.launch {
                    listState.animateScrollToItem(hour)
                }
            }

        }
    }
}