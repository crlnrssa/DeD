package com.example.ded.models

import com.example.ded.models.racas.iRaca

class Personagem(val raca: iRaca? = null) {

    var forca: Int = 8
    var destreza: Int = 8
    var constituicao: Int = 8
    var inteligencia: Int = 8
    var sabedoria: Int = 8
    var carisma: Int = 8

    val vida: Int
        get() = 10 + modificadorConstituicao // Vida base sempre 10 + modificador de constituição

    val modificadorForca: Int
        get() = calcularModificador(forca)

    val modificadorDestreza: Int
        get() = calcularModificador(destreza)

    val modificadorConstituicao: Int
        get() = calcularModificador(constituicao)

    val modificadorInteligencia: Int
        get() = calcularModificador(inteligencia)

    val modificadorSabedoria: Int
        get() = calcularModificador(sabedoria)

    val modificadorCarisma: Int
        get() = calcularModificador(carisma)

    init {
        // Aplica os bônus iniciais da raça, se houver
        raca?.AplicarBonus(this)
    }

    private fun calcularModificador(valor: Int): Int {
        return (valor - 10) / 2
    }

    fun exibirModificadores() {
        println("Modificadores:")
        println("Força: $modificadorForca")
        println("Destreza: $modificadorDestreza")
        println("Constituição: $modificadorConstituicao")
        println("Inteligência: $modificadorInteligencia")
        println("Sabedoria: $modificadorSabedoria")
        println("Carisma: $modificadorCarisma")
    }
}
