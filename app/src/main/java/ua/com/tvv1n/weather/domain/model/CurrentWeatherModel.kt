package ua.com.tvv1n.weather.domain.model

data class CurrentWeatherModel(
    val temp: Int,
    val latitude: Double,
    val longitude: Double,
    val name:String? = null
)
