package com.example.transmissoriotparacegos.providers

import androidx.lifecycle.MutableLiveData

class RegisteredSensors {
    val ips = MutableLiveData<List<String>>(listOf("192.168.0.200"))
}