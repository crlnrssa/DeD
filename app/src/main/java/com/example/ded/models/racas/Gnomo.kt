package models.racas

import com.example.ded.models.Personagem
import com.example.ded.models.racas.iRaca


class Gnomo : iRaca {
    override fun AplicarBonus(personagem: Personagem) {
        personagem.inteligencia += 2 // Aplica o bônus de 2 na inteligência

    }
}