package ua.com.tvv1n.weather.network.service

import ua.com.tvv1n.weather.domain.model.CityModel
import ua.com.tvv1n.weather.domain.model.CurrentWeatherModel
import ua.com.tvv1n.weather.domain.model.ForecastModel
import ua.com.tvv1n.weather.network.client.RetrofitClient

class RetrofitService : IService{
    override suspend fun getWeatherByCity(city: String, countryCode: String): CurrentWeatherModel {
        val currentWeatherApiRequest = RetrofitClient.getInstance().getRequest(
            "https://api.open-meteo.com",
            ICurrentWeatherService::class.java
        )
        val cityModel = getCityCoordinates(city,countryCode)

        val currentWeatherDto = currentWeatherApiRequest.getCurrentWeather(
            cityModel.latitude,
            cityModel.longitude,
            true,
            "auto"
        )

        return CurrentWeatherModel(
            currentWeatherDto.current_weather.temperature.toInt(),
            currentWeatherDto.latitude,
            currentWeatherDto.longitude,
            cityModel.name
        )

    }

    override suspend fun getWeatherForecast(city: String, countryCode: String): List<ForecastModel> {
        val forecastLists: ArrayList<ForecastModel> = ArrayList()
        val forecastApiRequest = RetrofitClient.getInstance().getRequest(
            "https://api.open-meteo.com",
            IForecastService::class.java
        )
        val cityModel = getCityCoordinates(city = city, countryCode = countryCode)

        val forecastDto = forecastApiRequest.getWeatherForecast(
            cityModel.latitude,
            cityModel.longitude,
            arrayOf("temperature_2m_max", "temperature_2m_min"),
            "auto"
        )

        for (i in 0 until forecastDto.daily.time.size) {
            val forecastModel = ForecastModel(
                forecastDto.daily.time[i],
                forecastDto.daily.temperature_2m_max[i].toDouble(),
                forecastDto.daily.temperature_2m_min[i].toDouble()
            )
            forecastLists.add(forecastModel)
        }

        return forecastLists
    }

    private suspend fun getCityCoordinates(city: String, countryCode: String): CityModel {
        val cityCoordinatesApiRequest = RetrofitClient.getInstance().getRequest(
            "https://geocoding-api.open-meteo.com",
            IGeocodingService::class.java
        )

        val cityCoordinates =
            cityCoordinatesApiRequest.getCoordinatesByCityName(city, countryCode, 1)

        return CityModel(
            cityCoordinates.results[0].latitude,
            cityCoordinates.results[0].longitude,
            cityCoordinates.results[0].name
        )
    }


}