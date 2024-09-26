package models.racas

import com.example.ded.models.Personagem
import com.example.ded.models.racas.iRaca


class GnomoFloresta : iRaca {
    override fun AplicarBonus(personagem: Personagem) {
        personagem.destreza += 1 // Aplica o bônus de 1 na destreza

    }
}