package com.arinspect.demo.network.repository

import com.arinspect.demo.network.WebApi
import com.arinspect.demo.network.model.DataModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DataRepository(private val webApi: WebApi) {

    fun getDataFromServer(): Observable<List<DataModel>> {

        return Observable.create { emitter ->
            webApi.getListData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isNotEmpty()) {
                        emitter.onNext(it)
                    }
                }, {
                    it.printStackTrace()
                })
        }
    }
}