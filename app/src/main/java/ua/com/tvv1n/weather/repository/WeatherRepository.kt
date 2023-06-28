package ua.com.tvv1n.weather.repository

import ua.com.tvv1n.weather.domain.model.CurrentWeatherModel
import ua.com.tvv1n.weather.domain.model.ForecastModel
import ua.com.tvv1n.weather.network.service.RetrofitService

class WeatherRepository() {
    private var retrofitService: RetrofitService = RetrofitService()
    suspend fun getCurrentWeatherByCity(city: String, countryCode: String): CurrentWeatherModel {

        return retrofitService.getWeatherByCity(city, countryCode)
    }

    suspend fun getWeatherForecast(city: String, countryCode: String): List<ForecastModel> {
        return retrofitService.getWeatherForecast(city, countryCode)
    }

}