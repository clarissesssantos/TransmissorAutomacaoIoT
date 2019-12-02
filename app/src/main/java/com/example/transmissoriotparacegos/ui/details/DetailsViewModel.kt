package com.example.transmissoriotparacegos.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.transmissoriotparacegos.models.ResultadoSensor
import com.example.transmissoriotparacegos.providers.SensorApi
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DetailsViewModel(ip: String) : ViewModel() {
    private var api: SensorApi? = null
    val resultado: MutableLiveData<ResultadoSensor> = MutableLiveData()
    init {
        api = SensorApi(ip)
        api!!.getTemperatura()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                resultado.postValue(it)
            }
    }
}

class DetailsViewModelFactory(val ip: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.cast(DetailsViewModel(ip))!!
    }
}
