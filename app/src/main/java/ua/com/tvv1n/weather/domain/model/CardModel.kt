package ua.com.tvv1n.weather.domain.model

data class CardModel(
    val cardName: String,
    val forecastModel: List<ForecastModel>
)