package ua.com.tvv1n.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.com.tvv1n.weather.databinding.FragmentWeatherBinding
import ua.com.tvv1n.weather.service.ICurrentWeatherService
import ua.com.tvv1n.weather.service.IForecastService
import ua.com.tvv1n.weather.service.IGeocondigService
import ua.com.tvv1n.weather.service.client.RetrofitObject
import ua.com.tvv1n.weather.ui.adapter.CardAdapter
import ua.com.tvv1n.weather.ui.model.CardModel
import ua.com.tvv1n.weather.ui.model.CurrentWeatherModel
import ua.com.tvv1n.weather.ui.model.ForecastModel
import ua.com.tvv1n.weather.ui.model.WeatherModel

class WeatherFragment : Fragment() {
    private var TAG = "WeatherFragment"
    private lateinit var binding: FragmentWeatherBinding
    private lateinit var adapter: CardAdapter
    private val model: WeatherModel by activityViewModels()
    private val forecastLists: ArrayList<ForecastModel> = ArrayList()

    private var cardList = listOf<CardModel>(
        CardModel("5-DAY FORECAST", forecastLists)

    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateCurrentWeatherValues()
        requestWeatherApi()

    }

    private fun updateCurrentWeatherValues() = with(binding) {
        model.liveDataCurrent.observe(viewLifecycleOwner) {
            currentWeatherContent.tvCurrentTemperature.text = it.temp.toString()
            menu.tvCity.text = it.name.toString()

        }

        model.liveForecastList.observe(viewLifecycleOwner) {
            initCardRcView()

        }
    }

    private fun requestWeatherApi() {
        val retrofitGeocoding = RetrofitObject.get("https://geocoding-api.open-meteo.com")
        val retrofitCurrentWeather = RetrofitObject.get("https://api.open-meteo.com")
        val retrofitForecastWeather = RetrofitObject.get("https://api.open-meteo.com")

        CoroutineScope(Dispatchers.Main).launch {
            val apiCoordinatesRequest = retrofitGeocoding.create(IGeocondigService::class.java)
            val cityCoordinates = apiCoordinatesRequest.getCoordinatesByCityName(
                "London", "uk", 1
            )

            CoroutineScope(Dispatchers.Main).launch {
                val apiCurrentWeatherRequest = retrofitCurrentWeather.create(ICurrentWeatherService::class.java)
                val currentWeatherResponse = apiCurrentWeatherRequest.getCurrentWeahter(
                    cityCoordinates.results[0].latitude, cityCoordinates.results[0].longitude, true, "auto"
                )

                val currentWeatherModel = CurrentWeatherModel(
                    currentWeatherResponse.current_weather.temperature.toInt(),
                    null,
                    null,
                    null,
                    cityCoordinates.results[0].name
                )
                model.liveDataCurrent.value = currentWeatherModel
            }

            CoroutineScope(Dispatchers.Main).launch {
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
                        forecastResponse.daily.temperature_2m_max[i],
                        forecastResponse.daily.temperature_2m_min[i]
                    )
                    forecastLists.add(forecastModel)
                }
                model.liveForecastList.value = forecastLists
            }
        }
    }

    private fun initCardRcView() = with(binding) {
        clRvCards.rvCard.layoutManager = LinearLayoutManager(activity)
        adapter = CardAdapter()
        clRvCards.rvCard.adapter = adapter
        adapter.submitList(cardList)
    }

    companion object {
        @JvmStatic
        fun newInstance() = WeatherFragment()
    }
}
