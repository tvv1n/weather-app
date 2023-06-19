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
import ua.com.tvv1n.weather.ui.adapter.CardAdapter
import ua.com.tvv1n.weather.domain.model.CardModel
import ua.com.tvv1n.weather.repository.CurrentWeatherRepository
import ua.com.tvv1n.weather.viewmodel.WeatherViewModel

class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    private lateinit var adapter: CardAdapter
    private val model: WeatherViewModel by activityViewModels()


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
        model.getLiveDataCurrentWeather().observe(viewLifecycleOwner) {
            currentWeatherContent.tvCurrentTemperature.text = it.temp.toString()
            menu.tvCity.text = it.name.toString()

        }

        model.getLiveDataForecastListWeather().observe(viewLifecycleOwner) {
            val cardList = arrayListOf(
                CardModel("5-DAY FORECAST", it)
            )
            initCardRcView(cardList)
        }
    }

    private fun requestWeatherApi() {
        model.mapCurrentWeather()
    }

    private fun initCardRcView(cardList: List<CardModel>) = with(binding) {
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
