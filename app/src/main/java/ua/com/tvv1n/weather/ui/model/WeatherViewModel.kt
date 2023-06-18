package ua.com.tvv1n.weather.ui.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.com.tvv1n.weather.service.ICurrentWeatherService
import ua.com.tvv1n.weather.service.IForecastService
import ua.com.tvv1n.weather.service.IGeocondigService
import ua.com.tvv1n.weather.service.client.RetrofitObject

class WeatherViewModel : ViewModel() {
    private val liveDataCurrent = MutableLiveData<CurrentWeatherModel>()
    private val liveForecastList = MutableLiveData<List<ForecastModel>>()

    fun getLiveDataCurrentWeather(): LiveData<CurrentWeatherModel> {
        return liveDataCurrent
    }

    fun getLiveDataForecastListWeather(): LiveData<List<ForecastModel>> {
        return liveForecastList
    }

    fun mapCurrentWeather() {
        val retrofitGeocoding = RetrofitObject.get("https://geocoding-api.open-meteo.com")
        val retrofitCurrentWeather = RetrofitObject.get("https://api.open-meteo.com")
        val retrofitForecastWeather = RetrofitObject.get("https://api.open-meteo.com")

        CoroutineScope(Dispatchers.Main).launch {
            val apiCoordinatesRequest = retrofitGeocoding.create(IGeocondigService::class.java)

            val cityCoordinates = apiCoordinatesRequest.getCoordinatesByCityName(
                "London", "uk", 1
            )

            val apiCurrentWeatherRequest =
                retrofitCurrentWeather.create(ICurrentWeatherService::class.java)
            val currentWeatherResponse = apiCurrentWeatherRequest.getCurrentWeahter(
                cityCoordinates.results[0].latitude,
                cityCoordinates.results[0].longitude,
                true,
                "auto"
            )

            val currentWeatherModel = CurrentWeatherModel(
                currentWeatherResponse.current_weather.temperature.toInt(),
                cityCoordinates.results[0].name
            )
            liveDataCurrent.value = currentWeatherModel

            val forecastLists: ArrayList<ForecastModel> = ArrayList()
            val apiForecastRequest = retrofitForecastWeather.create(IForecastService::class.java)
            val forecastResponse = apiForecastRequest.getWeatherForecast(
                cityCoordinates.results[0].latitude,
                cityCoordinates.results[0].longitude,
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
            liveForecastList.value = forecastLists
        }
    }
}
