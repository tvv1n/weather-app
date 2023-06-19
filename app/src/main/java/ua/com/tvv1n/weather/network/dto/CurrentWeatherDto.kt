package ua.com.tvv1n.weather.network.dto

data class CurrentWeatherEntity(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val current_weather: Weather
)

data class Weather(
    val temperature: Double,
    val time: String
)
