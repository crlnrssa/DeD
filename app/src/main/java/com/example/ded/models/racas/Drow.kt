package models.racas
import com.example.ded.models.Personagem
import com.example.ded.models.racas.iRaca

class Drow : iRaca {
    override fun AplicarBonus(personagem: Personagem) {
        personagem.carisma += 1 // Aplica o bônus de 1 na carisma

    }
}