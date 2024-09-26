package models.racas

import com.example.ded.models.Personagem
import com.example.ded.models.racas.iRaca

class Elfo : iRaca {
    override fun AplicarBonus(personagem: Personagem) {
        personagem.destreza += 2 // Aplica o bônus de 2 na destreza
    }
}