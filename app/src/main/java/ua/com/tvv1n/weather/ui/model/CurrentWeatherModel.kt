package ua.com.tvv1n.weather.ui.model

data class CurrentWeatherModel(
    val temp: Int,
    val minTemp: Int?=null,
    val maxTemp: Int?=null,
    val humidity: Int? = null,
    val name:String? = null
)
