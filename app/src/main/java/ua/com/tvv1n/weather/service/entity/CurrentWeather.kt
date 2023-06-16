package ua.com.tvv1n.weather.service.entity

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
