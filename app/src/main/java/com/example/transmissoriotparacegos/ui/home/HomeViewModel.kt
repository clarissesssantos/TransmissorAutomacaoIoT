package com.example.transmissoriotparacegos.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.transmissoriotparacegos.models.IoTDevice
import com.example.transmissoriotparacegos.providers.IoTApi
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class HomeViewModel(val ip: String) : ViewModel() {
    private val api = IoTApi(ip)
    lateinit var iotDevice: MutableLiveData<IoTDevice>
    init {
        api.getVariaveis()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                iotDevice.postValue(it)
            }
    }
}
