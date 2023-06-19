package ua.com.tvv1n.weather.network.dto

data class GeocodingModel(
    val results: List<CityList>
)

data class CityList(
    val name: String,
    val country_code: String,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val country: String

)