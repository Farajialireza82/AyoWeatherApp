package com.biblioklept.weatherapp.data.repository

import android.util.Log
import com.biblioklept.weatherapp.data.mappers.toWeatherInfo
import com.biblioklept.weatherapp.data.remote.WeatherApi
import com.biblioklept.weatherapp.domain.repository.WeatherRepository
import com.biblioklept.weatherapp.domain.util.Resource
import com.biblioklept.weatherapp.domain.weather.WeatherInfo
import java.lang.Exception
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat,
                    long
                ).toWeatherInfo()
            )

        } catch (e: Exception) {
            Log.wtf("ERROR WTF", e.localizedMessage)
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }
}