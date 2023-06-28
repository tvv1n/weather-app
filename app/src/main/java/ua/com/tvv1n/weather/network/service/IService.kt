package ua.com.tvv1n.weather.network.service

import ua.com.tvv1n.weather.domain.model.CurrentWeatherModel
import ua.com.tvv1n.weather.domain.model.ForecastModel

interface IService {
    suspend fun getWeatherByCity(city: String, countryCode: String): CurrentWeatherModel
    suspend fun getWeatherForecast(city: String, countryCode: String): List<ForecastModel>
}