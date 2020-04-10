package com.dream.sample.data.remote

import com.dream.sample.OpenForTesting
import com.dream.sample.data.model.Home
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://trulia-interview-challenge.herokuapp.com/"

@OpenForTesting
class TruliaRepository {

    private val truliaService by lazy { createService() }

    private fun createService(): TruliaService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(TruliaService::class.java)
    }

    fun getListings(start: Int = 0, count: Int = 25): Observable<List<Home>>{
        return truliaService.getListings(start = start, count = count)
    }

}

interface TruliaService {

    @GET("listings")
    fun getListings(@Query("start") start: Int, @Query("count") count: Int): Observable<List<Home>>
}