package com.example.weatherapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.weatherapp.R
import com.example.weatherapp.business.model.HourlyWeatherModel
import com.example.weatherapp.databinding.ItemMainHourlyBinding
import com.example.weatherapp.view.*

class MainHourlyListAdapter : BaseAdapter<HourlyWeatherModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val itemBinding = ItemMainHourlyBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return HourlyViewHolder(itemBinding)
    }

    inner class HourlyViewHolder(private val itemBinding: ItemMainHourlyBinding) :
        BaseViewHolder(itemBinding.root) {
        override fun bind(position: Int) {
            mData[position].apply {

                itemBinding.itemHourlyTimeTextView.text = dt.toDateFormatOf(HOUR_DOUBLE_DOT_MINUTE)
                itemBinding.itemHourlyTempTextView.text = StringBuilder().append(temp.toDegree()).append(" °").toString()
                itemBinding.itemHourlyPopTextView.text = pop.toPercentString(" %")
                itemBinding.itemHourlyWeatherConditionIcon.setImageResource(weather[0].icon.provideIcon())

            }
        }
    }
}

