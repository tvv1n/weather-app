package ua.com.tvv1n.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.com.tvv1n.weather.domain.model.CityModel
import ua.com.tvv1n.weather.domain.model.CurrentWeatherModel
import ua.com.tvv1n.weather.domain.model.ForecastModel
import ua.com.tvv1n.weather.repository.CurrentWeatherRepository
import ua.com.tvv1n.weather.repository.ForecastWeatherRepository
import ua.com.tvv1n.weather.repository.GeocodingRepository

class WeatherViewModel : ViewModel() {
    private val liveDataCurrentWeather = MutableLiveData<CurrentWeatherModel>()
    private val liveForecastList = MutableLiveData<List<ForecastModel>>()
    private var geoRepository: GeocodingRepository = GeocodingRepository()
    private var currentWeatherRepository: CurrentWeatherRepository = CurrentWeatherRepository()
    private var forecastRepository: ForecastWeatherRepository = ForecastWeatherRepository()

    fun getLiveDataCurrentWeather(): LiveData<CurrentWeatherModel> {
        return liveDataCurrentWeather
    }

    fun getLiveDataForecastListWeather(): LiveData<List<ForecastModel>> {
        return liveForecastList
    }

    fun mapCurrentWeather() {
        CoroutineScope(Dispatchers.Main).launch {
            val geoCityCoordinates: CityModel = geoRepository.getCityCoordinatesByName("Sumy", "ua")

            val currentWeather =
                currentWeatherRepository.getCurrentWeatherByName(geoCityCoordinates)
            liveDataCurrentWeather.value = currentWeather

            val forecastLists = forecastRepository.getWeatherForecast(geoCityCoordinates)
            liveForecastList.value = forecastLists
        }
    }
}
