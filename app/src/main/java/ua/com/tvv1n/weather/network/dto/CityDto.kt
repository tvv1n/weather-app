package ua.com.tvv1n.weather.network.dto

data class GeocodingDto(
    val results: List<CityListDto>
)

data class CityListDto(
    val name: String,
    val country_code: String,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val country: String

)