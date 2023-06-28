package ua.com.tvv1n.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.com.tvv1n.weather.domain.model.CurrentWeatherModel
import ua.com.tvv1n.weather.domain.model.ForecastModel
import ua.com.tvv1n.weather.repository.WeatherRepository

class WeatherViewModel : ViewModel() {
    private val liveDataCurrentWeather = MutableLiveData<CurrentWeatherModel>()
    private val liveForecastList = MutableLiveData<List<ForecastModel>>()
    private var weatherRepository: WeatherRepository = WeatherRepository()

    fun loadWeather(city: String, countryCode: String) {

        CoroutineScope(Dispatchers.Main).launch {
            val currentWeather =
                weatherRepository.getCurrentWeatherByCity(city, countryCode)
            liveDataCurrentWeather.value = currentWeather

            val weatherForecast = weatherRepository.getWeatherForecast(city, countryCode)
            liveForecastList.value = weatherForecast

        }
    }

    fun getLiveDataCurrentWeather(): LiveData<CurrentWeatherModel> {
        return liveDataCurrentWeather
    }

    fun getLiveDataForecastListWeather(): LiveData<List<ForecastModel>> {
        return liveForecastList
    }
}
