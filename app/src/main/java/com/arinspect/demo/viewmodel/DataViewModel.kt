package com.arinspect.demo.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arinspect.demo.network.model.DataModel
import com.arinspect.demo.network.repository.DataRepository

class DataViewModel(private val dataRepository: DataRepository) : ViewModel() {

    private val dataResponse: MutableLiveData<DataModel> = MutableLiveData()

    val responseModel: LiveData<DataModel> = dataResponse

    @SuppressLint("CheckResult")
    fun getDataFromServer() {
        dataRepository
            .getDataFromServer()
            .subscribe {
                dataResponse.postValue(it)
            }
    }
}