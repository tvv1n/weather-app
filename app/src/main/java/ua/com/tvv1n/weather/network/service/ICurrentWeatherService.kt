package ua.com.tvv1n.weather.network.service

import retrofit2.http.GET
import retrofit2.http.Query
import ua.com.tvv1n.weather.network.dto.CurrentWeatherEntity

interface ICurrentWeatherService {
    @GET("/v1/forecast?")
    suspend fun getCurrentWeahter(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current_weather") current_weather: Boolean,
        @Query("timezone") timezone: String
    ): CurrentWeatherEntity
}
