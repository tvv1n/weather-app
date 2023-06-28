package ua.com.tvv1n.weather.network.dto

data class CurrentWeatherDto(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val current_weather: WeatherDto
)

data class WeatherDto(
    val temperature: Double,
    val time: String
)
