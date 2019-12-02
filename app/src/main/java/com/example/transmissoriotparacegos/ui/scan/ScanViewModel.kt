package com.example.transmissoriotparacegos.ui.scan

import android.app.Application
import android.content.Context
import android.net.wifi.WifiManager
import android.text.format.Formatter
import android.util.Log
import androidx.lifecycle.*
import com.example.transmissoriotparacegos.models.IoTDevice
import com.example.transmissoriotparacegos.models.ResultadoSensor
import com.example.transmissoriotparacegos.providers.RegisteredSensors
import com.example.transmissoriotparacegos.providers.SensorApi
import kotlinx.coroutines.launch
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ScanViewModel(application: LifecycleOwner, val wm: WifiManager) : ViewModel() {
    private var api: SensorApi? = null
    private val registeredIps = RegisteredSensors()
    val devices: MutableLiveData<List<IoTDevice>> = MutableLiveData(listOf())
    init {
        registeredIps.ips.observe(application, Observer {
            for (ip in it) {
                val deviceList = devices.value!!.toMutableList()
                api = SensorApi(ip)
                api!!.getNome()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { device ->
                        deviceList.add(device)
                        devices.postValue(deviceList)
                    }
            }
        })
    }

}

class ScanViewModelFactory(val wm: WifiManager, val application: LifecycleOwner) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.cast(ScanViewModel(application, wm))!!
    }
}
