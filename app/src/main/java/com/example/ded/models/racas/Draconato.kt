package models.racas
import com.example.ded.models.Personagem
import com.example.ded.models.racas.iRaca

class Draconato : iRaca {
    override fun AplicarBonus(personagem: Personagem) {
        personagem.forca += 2 // Aplica o bônus de 2 na força
        personagem.carisma += 1 // Aplica o bônus de 1 na carisma

    }
}