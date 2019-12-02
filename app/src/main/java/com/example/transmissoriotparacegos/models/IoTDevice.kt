package com.example.transmissoriotparacegos.models

import com.google.gson.annotations.SerializedName

data class IoTDevice(
    val nome: String,
    val ip: String
)

data class ResultadoSensor(
    val i: String,
    @SerializedName("Valor")
    val valor: Float,
    @SerializedName("Unidade")
    val unidade: String
)

data class Capacidade(
    val id: Int,
    val tipo: TipoSensor,
    val unidade: String,
    val descricao: String
)

enum class TipoSensor(val tipo: String) {
    TEMPERATURA("temperatura")
}