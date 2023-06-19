package ua.com.tvv1n.weather.repository

import ua.com.tvv1n.weather.domain.model.CityModel
import ua.com.tvv1n.weather.network.client.RetrofitClient
import ua.com.tvv1n.weather.network.service.IGeocondigService


class GeocodingRepository {
    private var retrofitService: RetrofitClient = RetrofitClient()

    suspend fun getCityCoordinatesByName(city: String, counryCode: String): CityModel {
        val retrofitGeocoding = retrofitService.getRequest(
            "https://geocoding-api.open-meteo.com",
            IGeocondigService::class.java
        )
        val cityCoordinates = retrofitGeocoding.getCoordinatesByCityName(city, counryCode, 1)

        return CityModel(
            cityCoordinates.results[0].latitude,
            cityCoordinates.results[0].longitude,
            cityCoordinates.results[0].name
        )
    }
}