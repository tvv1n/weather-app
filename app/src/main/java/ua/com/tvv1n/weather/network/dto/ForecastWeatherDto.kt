package ua.com.tvv1n.weather.network.dto

data class ForecastWeatherEntity(
    val daily: Date
)

data class Date(
    val time: ArrayList<String>,
    val temperature_2m_max: ArrayList<String>,
    val temperature_2m_min: ArrayList<String>
)

