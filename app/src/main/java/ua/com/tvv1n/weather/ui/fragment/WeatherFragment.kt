package ua.com.tvv1n.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ua.com.tvv1n.weather.databinding.FragmentWeatherBinding
import ua.com.tvv1n.weather.ui.adapter.CardAdapter
import ua.com.tvv1n.weather.ui.model.CardModel
import ua.com.tvv1n.weather.ui.model.ForecastModel

class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    private lateinit var adapter: CardAdapter


    private var forecastList = listOf<ForecastModel>(
        ForecastModel("Monday", "12"),
        ForecastModel("Tuesday", "13"),
        ForecastModel("Wednesday", "14"),
        ForecastModel("Thursday", "15"),
        ForecastModel("Friday", "16"),
        ForecastModel("Saturday", "17"),
        ForecastModel("Sunday", "18"),
    )

    private var cardList = listOf<CardModel>(
        CardModel("card 1", forecastList),
        CardModel("card 2", forecastList),
        CardModel("card 3", forecastList),
        CardModel("card 4", forecastList),
        CardModel("card 5", forecastList),
        CardModel("card 6", forecastList)
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
        initCardRcView()
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
