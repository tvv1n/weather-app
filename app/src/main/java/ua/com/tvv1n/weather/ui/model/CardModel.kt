package ua.com.tvv1n.weather.ui.model

import androidx.lifecycle.LiveData

data class CardModel(
    val cardName: String,
    val forecastModel: List<ForecastModel>
)