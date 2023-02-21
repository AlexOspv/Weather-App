package com.example.weatherapp.presenters

import com.example.weatherapp.view.MainView

class MainPresenter : BasePresenter<MainView>() {
    //TODO variable repository

    override fun enable() {

    }

    fun refresh (lat: String, lon: String) {
        viewState.setLoading(true)
        // TODO call repository
    }

}