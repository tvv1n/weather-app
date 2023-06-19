package ua.com.tvv1n.weather.repository

import ua.com.tvv1n.weather.domain.model.CityModel
import ua.com.tvv1n.weather.domain.model.CurrentWeatherModel
import ua.com.tvv1n.weather.network.client.RetrofitClient
import ua.com.tvv1n.weather.network.service.ICurrentWeatherService

class CurrentWeatherRepository() {
    private var retrofitService: RetrofitClient = RetrofitClient()

    suspend fun getCurrentWeatherByName(geoCityCoordinates: CityModel): CurrentWeatherModel {

        val retrofitCurrentWeather = retrofitService.getRequest(
            "https://api.open-meteo.com",
            ICurrentWeatherService::class.java
        )
        val currentWeather =
            retrofitCurrentWeather.getCurrentWeahter(
                geoCityCoordinates.latitude, geoCityCoordinates.longitude,
                true,
                "auto"
            )
        return CurrentWeatherModel(
            currentWeather.current_weather.temperature.toInt(),
            currentWeather.latitude,
            currentWeather.longitude,
            geoCityCoordinates.name
        )
    }
}