package com.biblioklept.weatherapp.domain.repository

import com.biblioklept.weatherapp.domain.util.Resource
import com.biblioklept.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long:Double): Resource<WeatherInfo>
}