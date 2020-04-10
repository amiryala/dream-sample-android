package com.dream.sample.data

import com.dream.sample.OpenForTesting
import com.dream.sample.data.model.Home
import com.dream.sample.data.remote.TruliaRepository
import io.reactivex.Observable

const val LIST_PAGE_COUNT = 20

@OpenForTesting
class HomeListingsInteractor(private val truliaRepository: TruliaRepository = TruliaRepository()){

    fun getListings(start: Int = 0): Observable<List<Home>>{
        return  truliaRepository.getListings(start, LIST_PAGE_COUNT)
    }
}