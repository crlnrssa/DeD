package models.racas

import com.example.ded.models.Personagem
import com.example.ded.models.racas.iRaca


class MeioElfo : iRaca {
    override fun AplicarBonus(personagem: Personagem) {
        personagem.carisma += 2 // Aplica o bônus de 2 na carisma

    }
}