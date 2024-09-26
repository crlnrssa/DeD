package models.racas

import com.example.ded.models.Personagem
import com.example.ded.models.racas.iRaca

class Anao : iRaca {
    override fun AplicarBonus(personagem: Personagem) {
        personagem.constituicao += 2 // Aplica o bônus de 2 na constituição
    }
}
