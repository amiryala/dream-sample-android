package com.dream.sample.ui.home.util

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

private const val VISIBLE_THRESHOLD_DEFAULT = 15

/**
 * Extension function for RecyclerView
 *
 * Adds an OnScrollListener to the RecyclerView which will observe scroll changes.
 *
 * When the user has scrolled beyond the fold/threshold defined the paginate lambda will be executed.
 *
 * [paginationThreshold] - Number of items UP from the BOTTOM of the list which will signal a
 * pagination behavior, giving an opportunity to load more datain for 'infinite' scrolling behaviors.
 *
 * Alter this from the default if the number of page fetches is too/not frequent enough for your use case.
 *
 * [paginate] - lambda with user-specific behaviors to execute for a pagination event.
 */
fun RecyclerView.addPaginationListener(paginationThreshold: Int = VISIBLE_THRESHOLD_DEFAULT,
                                       paginate: () -> Unit) {

    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {

        val layoutManager = this@addPaginationListener.layoutManager

        var visibleItemCount = 0
        var totalItemCount = 0
        var firstVisibleItemPosition = 0

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            visibleItemCount = layoutManager?.childCount ?: 0
            totalItemCount = layoutManager?.itemCount ?: 0
            firstVisibleItemPosition = getFirstCompletelyVisibleItemPosition()

            if ((totalItemCount - visibleItemCount) <= (firstVisibleItemPosition + paginationThreshold)) {
                paginate()
            }
        }

        /**
         * Retrieves the first completely visible item position in the list.
         *
         * Unfortunately LayoutManager does not have a polymorphic API for this behavior.
         */
        fun getFirstCompletelyVisibleItemPosition() = when(layoutManager) {
            is LinearLayoutManager -> layoutManager.findFirstCompletelyVisibleItemPosition()
            is GridLayoutManager -> layoutManager.findFirstCompletelyVisibleItemPosition()
            is StaggeredGridLayoutManager -> {
                val firstVisibleItems = layoutManager.findFirstCompletelyVisibleItemPositions(null)
                if (firstVisibleItems.isNotEmpty()) firstVisibleItems[0] else 0
            }
            else -> {
                throw NotImplementedError("Unimplemented Pagination support for LayoutManager Type.")
            }
        }
    })
}
