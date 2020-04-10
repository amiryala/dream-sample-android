package com.dream.sample.ui.home.listing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dream.sample.R
import com.dream.sample.data.model.Home
import com.dream.sample.ui.home.detail.startHomeDetailActivity
import com.dream.sample.ui.home.util.addPaginationListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home_listing.*

class HomeListingActivity : AppCompatActivity(), HomeListingView {

    private val homeListingPresenter by lazy { HomeListingPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_listing)

        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        recycler_view.adapter = HomeListingAdapter{ homeListingPresenter.selectedHome(it) }

        recycler_view.addPaginationListener { homeListingPresenter.loadNextPage() }

        homeListingPresenter.retrieveListings()
    }

    override fun onDestroy() {
        super.onDestroy()
        homeListingPresenter.unsubscribe()
    }

    override fun refreshListing(list: List<Home>) {
        (recycler_view.adapter as HomeListingAdapter).apply{
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun addListing(list: List<Home>) {
        (recycler_view.adapter as HomeListingAdapter).apply{
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun startHomeDetail(home: Home) {
        startHomeDetailActivity(home)
    }

    override fun isLoading(): Boolean {
        return progress_bar.visibility == View.VISIBLE
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.INVISIBLE
    }

    override fun showError(message: String) {
        Snackbar.make(root, message, Snackbar.LENGTH_SHORT).show()
    }
}
