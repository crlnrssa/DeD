package models.racas

import com.example.ded.models.Personagem
import com.example.ded.models.racas.iRaca


class AltoElfo : iRaca {
    override fun AplicarBonus(personagem: Personagem) {
        personagem.inteligencia += 1 // Aplica o bônus de 1 na inteligência

    }
}