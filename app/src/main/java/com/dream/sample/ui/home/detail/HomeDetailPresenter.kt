package com.dream.sample.ui.home.detail

import com.dream.sample.data.model.Home

class HomeDetailPresenter(private  val view: HomeDetailView) {
    fun retrieveImage(home: Home) {
        home.photos?.let {
            view.apply {
                showLoading()
                view.refreshListing(it)
                hideLoading()
            }
        }
    }

}