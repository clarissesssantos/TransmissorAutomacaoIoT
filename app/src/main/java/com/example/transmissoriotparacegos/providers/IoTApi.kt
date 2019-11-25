package com.example.transmissoriotparacegos.providers

import com.example.transmissoriotparacegos.models.IoTDevice
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

class IoTApi(val ip: String) {
    var service: IoTServiceDef
    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder().apply {
            baseUrl("$ip/")
            addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            addConverterFactory((GsonConverterFactory.create(gson)))
            client(httpClient.build())
        }.build()
        service = retrofit.create<IoTServiceDef>(IoTServiceDef::class.java)
    }

    fun getVariaveis() : Observable<IoTDevice> {
        return service.getVariaveis()
            .map {
                IoTDevice(it.nome, it.descricao, it.capacidades)
            }
    }
}