package ua.com.tvv1n.weather.network.service

import retrofit2.http.GET
import retrofit2.http.Query
import ua.com.tvv1n.weather.network.dto.GeocodingModel

interface IGeocondigService {
    @GET("/v1/search?")
    suspend fun getCoordinatesByCityName(
        @Query("name") city: String,
        @Query("country_code") country_code: String,
        @Query("count") count: Int
    ): GeocodingModel
}