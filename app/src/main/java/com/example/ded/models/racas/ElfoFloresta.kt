package models.racas

import com.example.ded.models.Personagem
import com.example.ded.models.racas.iRaca


class ElfoFloresta : iRaca {
    override fun AplicarBonus(personagem: Personagem) {
        personagem.sabedoria += 1 // Aplica o bônus de 1 na sabedoria
    }
}