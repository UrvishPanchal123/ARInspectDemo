package com.arinspect.demo.network

import com.arinspect.demo.network.repository.DataRepository

object RepositoryFactory {

    fun createDataRepository(): DataRepository {
        val webApi = RestUtil.instance.retrofit.create(WebApi::class.java)
        return DataRepository(webApi)
    }
}