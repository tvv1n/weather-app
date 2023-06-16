package ua.com.tvv1n.weather.ui.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherModel:ViewModel() {
    val liveDataCurrent = MutableLiveData<CurrentWeatherModel>()
    val liveForecastList = MutableLiveData<List<ForecastModel>>()
}