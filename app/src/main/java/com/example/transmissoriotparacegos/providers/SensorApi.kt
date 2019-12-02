package com.example.transmissoriotparacegos.providers

import com.example.transmissoriotparacegos.models.IoTDevice
import com.example.transmissoriotparacegos.models.ResultadoSensor
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

class SensorApi(var ip: String) {
    lateinit var service: SensorServiceDef
    init {
        rebuildFromIP()
    }

    fun rebuildFromIP(ip: String = this.ip) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder().apply {
            baseUrl("http://$ip")
            addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            addConverterFactory((GsonConverterFactory.create(gson)))
            client(httpClient.build())
        }.build()
        service = retrofit.create<SensorServiceDef>(SensorServiceDef::class.java)
    }

    fun getTemperatura() : Observable<ResultadoSensor> {
        return service.getTemperatura()
            .map {
                ResultadoSensor(it.i, it.valor, it.unidade)
            }
    }

    fun getUmidade() : Observable<ResultadoSensor> {
        return service.getUmidade()
            .map {
                ResultadoSensor(it.i, it.valor, it.unidade)
            }
    }

    fun getNome() : Observable<IoTDevice> {
        return service.getNome()
            .map {
                IoTDevice(it, ip)
            }
    }
}