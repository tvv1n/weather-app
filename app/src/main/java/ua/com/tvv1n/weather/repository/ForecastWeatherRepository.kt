package ua.com.tvv1n.weather.repository

import ua.com.tvv1n.weather.domain.model.CityModel
import ua.com.tvv1n.weather.domain.model.ForecastModel
import ua.com.tvv1n.weather.network.client.RetrofitClient
import ua.com.tvv1n.weather.network.service.IForecastService

class ForecastWeatherRepository {
    private var retrofitService: RetrofitClient = RetrofitClient()

    suspend fun getWeatherForecast(city:CityModel): List<ForecastModel> {
        val forecastLists: ArrayList<ForecastModel> = ArrayList()
        val retrofitForecastWeather = retrofitService.getRequest(
            "https://api.open-meteo.com",
            IForecastService::class.java
        )

        val forecastResponse = retrofitForecastWeather.getWeatherForecast(
            city.latitude,
            city.longitude,
            arrayOf("temperature_2m_max", "temperature_2m_min"),
            "auto"
        )
        for (i in 0 until forecastResponse.daily.time.size) {
            val forecastModel = ForecastModel(
                forecastResponse.daily.time[i],
                forecastResponse.daily.temperature_2m_max[i].toDouble(),
                forecastResponse.daily.temperature_2m_min[i].toDouble()
            )
            forecastLists.add(forecastModel)
        }
        return forecastLists
    }
}