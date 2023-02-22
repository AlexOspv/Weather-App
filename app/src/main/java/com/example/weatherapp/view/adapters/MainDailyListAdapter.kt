package com.example.weatherapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.weatherapp.R
import com.example.weatherapp.business.model.DailyWeatherModel
import com.example.weatherapp.databinding.ItemMainDailyBinding
import com.example.weatherapp.view.*

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
            mData[position].apply {

                itemBinding.itemDailyDateTextView.text = dt.toDateFormatOf(DAY_WEEK_NAME_LONG)
                itemBinding.itemDailyMinTempTextView.text = StringBuilder().append(temp.min.toDegree()).append(" °").toString()
                itemBinding.itemDailyMaxTempTextView.text = StringBuilder().append(temp.max.toDegree()).append(" °").toString()
                itemBinding.itemDailyPopTextView.text = pop.toPercentString(" %")
                itemBinding.itemDailyWeatherConditionIcon.setImageResource(weather[0].icon.provideIcon())

            }

        }
    }

}