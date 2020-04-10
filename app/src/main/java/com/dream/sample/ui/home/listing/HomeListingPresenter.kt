package com.dream.sample.ui.home.listing

import com.dream.sample.data.HomeListingsInteractor
import com.dream.sample.data.model.Home
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeListingPresenter (private val view: HomeListingView,
                            private val homeListingsInteractor: HomeListingsInteractor = HomeListingsInteractor(),
                            private val compositeDisposable: CompositeDisposable = CompositeDisposable()) {

    private var pageIndex: Int = 0
    private var paginationCompleted: Boolean = false

    fun retrieveListings() {


        // Prevent duplicate calls
        if(view.isLoading()){ return }

        pageIndex = 0
        paginationCompleted = false

        view.showLoading()
        compositeDisposable.add(homeListingsInteractor.getListings(pageIndex)
            .distinct()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally(view::hideLoading)
            .subscribe(view::refreshListing, this::onError))
    }

    fun loadNextPage() {

        //Prevent duplicate calls from user action and stop pagination when list returns empty
        if(view.isLoading() || paginationCompleted){ return }
        view.showLoading()
        pageIndex = pageIndex.inc()

        compositeDisposable.add(homeListingsInteractor.getListings(pageIndex)
            .distinct()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally(view::hideLoading)
            .subscribe({list ->

                if(list.isNullOrEmpty()) {
                    paginationCompleted = true
                } else {
                    view.addListing(list)
                }
            }, {
                pageIndex.dec()
                this.onError(it)
            }))
    }

    fun selectedHome(home: Home) {
        if(home.photos.isNullOrEmpty()){
            view.showError("This home has no photos available.")
            return
        }
        view.startHomeDetail(home)
    }

    private fun onError(throwable: Throwable) {
        throwable.printStackTrace()
        val message = throwable.message?.let {it} ?: kotlin.run { "Something went wrong."}
        view.showError(message)
    }

    fun unsubscribe(){
        compositeDisposable.clear()
    }
}