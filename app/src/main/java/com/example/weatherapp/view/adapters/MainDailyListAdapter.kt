package com.example.weatherapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.weatherapp.R
import com.example.weatherapp.business.model.DailyWeatherModel
import com.example.weatherapp.databinding.ItemMainDailyBinding

class MainDailyListAdapter : BaseAdapter<DailyWeatherModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val itemBinding = ItemMainDailyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return DailyViewHolder(itemBinding)
    }

    inner class DailyViewHolder(private val itemBinding: ItemMainDailyBinding) :
        BaseViewHolder(itemBinding.root) {
        override fun bind(position: Int) {
            itemBinding.itemDailyDateTextView.text = "13 Monday"
            itemBinding.itemDailyMinTempTextView.text = "15°"
            itemBinding.itemDailyMaxTempTextView.text = "19°"
            itemBinding.itemDailyPopTextView.text = "17%"
            itemBinding.itemDailyWeatherConditionIcon.setImageResource(R.drawable.ic_sunny_24)
        }
    }

}