package ua.com.tvv1n.weather.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ua.com.tvv1n.weather.domain.model.CardModel
import ua.com.tvv1n.weather.R
import ua.com.tvv1n.weather.databinding.CardRvItemLayoutBinding

class CardAdapter : ListAdapter<CardModel, CardAdapter.Holder>(Comparator()) {
    private var TAG = "CardAdapter"
    private lateinit var adapter: ForecastAdapter

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CardRvItemLayoutBinding.bind(view)

        fun bind(item: CardModel) = with(binding) {
            tvCardTitle.text = item.cardName
        }
    }

    class Comparator : DiffUtil.ItemCallback<CardModel>() {
        override fun areItemsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_rv_item_layout, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
        holder.binding.rvCardContent.layoutManager = LinearLayoutManager(holder.itemView.context)
        adapter = ForecastAdapter()
        holder.binding.rvCardContent.adapter = adapter
        adapter.submitList(getItem(position).forecastModel)

    }
}