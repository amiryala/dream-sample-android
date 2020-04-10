package com.dream.sample.ui.home.listing

import com.dream.sample.data.model.Home

interface HomeListingView {
    fun showError(message: String)
    fun startHomeDetail(home: Home)
    fun isLoading(): Boolean
    fun showLoading()
    fun hideLoading()
    fun addListing(list: List<Home>)
    fun refreshListing(list: List<Home>)
}