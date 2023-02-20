package com.example.weatherapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.weatherapp.R
import com.example.weatherapp.business.model.HourlyWeatherModel
import com.example.weatherapp.databinding.ItemMainHourlyBinding


class MainHourlyListAdapter : BaseAdapter<HourlyWeatherModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val itemBinding = ItemMainHourlyBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return HourlyViewHolder(itemBinding)
    }

    inner class HourlyViewHolder(private val itemBinding: ItemMainHourlyBinding) :
        BaseViewHolder(itemBinding.root) {
        override fun bind(position: Int) {
            itemBinding.itemHourlyTimeTextView.text = "15:00"
            itemBinding.itemHourlyTempTextView.text = "15°"
            itemBinding.itemHourlyPopTextView.text = "99%"
            itemBinding.itemHourlyWeatherConditionIcon.setImageResource(R.drawable.ic_sunny_24)
        }
    }

}

