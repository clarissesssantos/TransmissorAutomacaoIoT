package com.example.transmissoriotparacegos.providers

import com.example.transmissoriotparacegos.models.IoTDevice
import retrofit2.http.GET
import rx.Observable

interface IoTServiceDef {
    @GET("/variavel")
    fun getVariaveis(): Observable<IoTDevice>
}