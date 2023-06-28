package ua.com.tvv1n.weather.network.dto

data class ForecastWeatherDto(
    val time: ArrayList<String>,
    val temperature_2m_max: ArrayList<String>,
    val temperature_2m_min: ArrayList<String>
)

data class ForecastDailyDto(
    val daily: ForecastWeatherDto
)

