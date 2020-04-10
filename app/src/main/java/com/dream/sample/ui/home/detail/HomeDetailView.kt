package com.dream.sample.ui.home.detail

interface HomeDetailView {

    fun showLoading()

    fun hideLoading()

    fun refreshListing(list: List<String>)
}