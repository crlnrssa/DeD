package models.racas

import com.example.ded.models.Personagem
import com.example.ded.models.racas.iRaca

class HalflingRobusto : iRaca {
    override fun AplicarBonus(personagem: Personagem) {
        personagem.constituicao += 1 // Aplica o bônus de 1 na constituição

    }
}