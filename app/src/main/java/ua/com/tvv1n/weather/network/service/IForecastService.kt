package ua.com.tvv1n.weather.network.service

import retrofit2.http.GET
import retrofit2.http.Query
import ua.com.tvv1n.weather.network.dto.ForecastWeatherEntity

interface IForecastService {
    @GET("v1/forecast?")
    suspend fun getWeatherForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("daily") daily: Array<String>,
        @Query("timezone") timezone: String
    ): ForecastWeatherEntity
}
