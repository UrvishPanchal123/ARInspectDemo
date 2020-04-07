package com.arinspect.demo.network

import com.arinspect.demo.network.model.DataModel
import io.reactivex.Single
import retrofit2.http.GET

interface WebApi {

    @GET("facts.json")
    fun getListData(): Single<List<DataModel>>
}