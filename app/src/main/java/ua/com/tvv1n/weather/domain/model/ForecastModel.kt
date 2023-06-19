package ua.com.tvv1n.weather.domain.model

data class ForecastModel(
    val time: String,
    val temp_max: Double,
    val temp_min: Double
)