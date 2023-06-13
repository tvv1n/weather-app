package ua.com.tvv1n.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ua.com.tvv1n.weather.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    private lateinit var adapter: CardAdapter

    private var list = listOf<CardModel>(
        CardModel("forecast for 5 days"),
        CardModel("forecast for 15 days"),
        CardModel("forecast for 5 days"),
        CardModel("forecast for 15 days"),
        CardModel("forecast for 5 days"),
        CardModel("forecast for 15 days")
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
        adapter.submitList(list)
//        rvCard.layoutManager = LinearLayoutManager(activity)
//        adapter = CardAdapter()
//        rvCard.adapter = adapter
//        adapter.submitList(list)
    }

    companion object {
        @JvmStatic
        fun newInstance() = WeatherFragment()
    }
}
