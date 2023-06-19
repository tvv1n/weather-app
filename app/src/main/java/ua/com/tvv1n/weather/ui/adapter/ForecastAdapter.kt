package ua.com.tvv1n.weather.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ua.com.tvv1n.weather.R
import ua.com.tvv1n.weather.databinding.CardContentRvItemBinding
import ua.com.tvv1n.weather.domain.model.ForecastModel
import ua.com.tvv1n.weather.util.Util

class ForecastAdapter : ListAdapter<ForecastModel, ForecastAdapter.Holder>(Comparator()) {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CardContentRvItemBinding.bind(view)
        fun bind(item: ForecastModel) = with(binding) {

            val days = Util.newInstance()
                .dateFormatter(item.time, "yyyy-MM-dd", "EEE")

            tvDay.text = days
            tvTempMin.text = item.temp_min.toInt().toString()
            tvTempMax.text = item.temp_max.toInt().toString()
        }
    }

    class Comparator : DiffUtil.ItemCallback<ForecastModel>() {
        override fun areItemsTheSame(oldItem: ForecastModel, newItem: ForecastModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ForecastModel, newItem: ForecastModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_content_rv_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}