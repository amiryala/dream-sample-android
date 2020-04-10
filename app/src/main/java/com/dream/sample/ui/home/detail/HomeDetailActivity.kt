package com.dream.sample.ui.home.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dream.sample.R
import com.dream.sample.data.model.Home
import kotlinx.android.synthetic.main.activity_home_detail.*

const val KEY_HOME = "KEY_HOME"

fun Context.startHomeDetailActivity(home: Home) {
    val intent = Intent(this, HomeDetailActivity::class.java)
    intent.putExtra(KEY_HOME, home)
    startActivity(intent)
}

class HomeDetailActivity : AppCompatActivity(), HomeDetailView {

    private val homeDetailPresenter by lazy { HomeDetailPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_detail)

        recycler_view.adapter = HomeImageAdapter()
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))

        intent.getParcelableExtra<Home>(KEY_HOME)?.let {
            homeDetailPresenter.retrieveImage(it)
        }
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.INVISIBLE
    }

    override fun refreshListing(list: List<String>) {
        (recycler_view.adapter as HomeImageAdapter).apply{
            this.list = list
            notifyDataSetChanged()
        }
    }
}