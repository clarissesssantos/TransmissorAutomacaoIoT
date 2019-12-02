package com.example.transmissoriotparacegos.providers

import com.example.transmissoriotparacegos.models.IoTDevice
import com.example.transmissoriotparacegos.models.ResultadoSensor
import retrofit2.http.GET
import rx.Observable

interface SensorServiceDef {
    @GET("/variavel?i=temperatura")
    fun getTemperatura(): Observable<ResultadoSensor>

    @GET("/variavel?i=umidade")
    fun getUmidade(): Observable<ResultadoSensor>

    @GET("/whoareyou")
    fun getNome(): Observable<String>
}