package com.example.ded

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ded.models.Personagem
import models.racas.AltoElfo
import models.racas.Anao
import models.racas.AnaoMontanha
import models.racas.Draconato
import models.racas.Drow
import models.racas.Elfo
import models.racas.ElfoFloresta
import models.racas.Gnomo
import models.racas.GnomoFloresta
import models.racas.GnomoRochas
import models.racas.Halfling
import models.racas.HalflingPesLeves
import models.racas.HalflingRobusto
import models.racas.Humano
import models.racas.MeioElfo
import models.racas.MeioOrc
import models.racas.Tiefling
import models.racas.anaos.AnaoColina

class DistribuicaoPontosActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Obter a raça escolhida da Intent
            val racaEscolhida = intent.getStringExtra("raca")
            val navController = rememberNavController()

            // Chama a função Composable
            DistribuicaoPontosScreen(navController, racaEscolhida)
        }
    }
}

@Composable
fun DistribuicaoPontosScreen(navController: NavHostController, raca: String?) {
    // Criação de um personagem
    val personagem = remember { Personagem() }

    // Mapeamento de raças para suas classes
    val racaObj = when (raca) {
        "AltoElfo" -> AltoElfo()
        "Anao" -> Anao()
        "AnaoColina" -> AnaoColina()
        "AnaoMontanha" -> AnaoMontanha()
        "Draconato" -> Draconato()
        "Drow" -> Drow()
        "Elfo" -> Elfo()
        "ElfoFloresta" -> ElfoFloresta()
        "Gnomo" -> Gnomo()
        "GnomoFloresta" -> GnomoFloresta()
        "GnomoRochas" -> GnomoRochas()
        "Halfling" -> Halfling()
        "HalflingPesLeves" -> HalflingPesLeves()
        "HalflingRobusto" -> HalflingRobusto()
        "Humano" -> Humano()
        "MeioElfo" -> MeioElfo()
        "MeioOrc" -> MeioOrc()
        "Tiefling" -> Tiefling()
        else -> null
    }

    // Aplica o bônus da raça ao personagem, se existir
    racaObj?.AplicarBonus(personagem)

    // Inicializa os atributos a partir do valor base
    val forca = remember { mutableStateOf(personagem.forca) }
    val destreza = remember { mutableStateOf(personagem.destreza) }
    val constituicao = remember { mutableStateOf(personagem.constituicao) }
    val inteligencia = remember { mutableStateOf(personagem.inteligencia) }
    val sabedoria = remember { mutableStateOf(personagem.sabedoria) }
    val carisma = remember { mutableStateOf(personagem.carisma) }
    val pontosDisponiveis = remember { mutableStateOf(27) }

    // Função para alocar pontos com validação
    fun alocarPontos(atributoAtual: MutableState<Int>, novoValor: Int, minimo: Int) {
        val diferenca = novoValor - atributoAtual.value

        // Se a diferença é positiva (tentativa de adicionar pontos)
        if (diferenca > 0) {
            if (pontosDisponiveis.value >= diferenca && novoValor <= 15) {
                atributoAtual.value = novoValor
                pontosDisponiveis.value -= diferenca
            }
        } else {
            // Se a diferença é negativa (tentativa de remover pontos)
            if (novoValor >= minimo) {
                atributoAtual.value = novoValor
                pontosDisponiveis.value -= diferenca // Adiciona os pontos removidos de volta
            }
        }
    }

    // Calcula o valor mínimo para cada atributo, levando em consideração o bônus
    val forcaMinima = remember { personagem.forca } // Base é o valor atual do personagem
    val destrezaMinima = remember { personagem.destreza }
    val constituicaoMinima = remember { personagem.constituicao }
    val inteligenciaMinima = remember { personagem.inteligencia }
    val sabedoriaMinima = remember { personagem.sabedoria }
    val carismaMinima = remember { personagem.carisma }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Distribuição de Pontos", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Raça escolhida: $raca")
        Text(text = "Pontos disponíveis: ${pontosDisponiveis.value}")

        // Força
        Text(text = "Força: ${forca.value}")
        Slider(
            value = forca.value.toFloat(),
            onValueChange = { newValue -> alocarPontos(forca, newValue.toInt(), forcaMinima) },
            valueRange = forcaMinima.toFloat()..15f,
            steps = (15 - forcaMinima)
        )

        // Destreza
        Text(text = "Destreza: ${destreza.value}")
        Slider(
            value = destreza.value.toFloat(),
            onValueChange = { newValue -> alocarPontos(destreza, newValue.toInt(), destrezaMinima) },
            valueRange = destrezaMinima.toFloat()..15f,
            steps = (15 - destrezaMinima)
        )

        // Constituição (não pode ser menor que constituicaoMinima)
        Text(text = "Constituição: ${constituicao.value}")
        Slider(
            value = constituicao.value.toFloat(),
            onValueChange = { newValue -> alocarPontos(constituicao, newValue.toInt(), constituicaoMinima) },
            valueRange = constituicaoMinima.toFloat()..15f,
            steps = (15 - constituicaoMinima)
        )

        // Inteligência
        Text(text = "Inteligência: ${inteligencia.value}")
        Slider(
            value = inteligencia.value.toFloat(),
            onValueChange = { newValue -> alocarPontos(inteligencia, newValue.toInt(), inteligenciaMinima) },
            valueRange = inteligenciaMinima.toFloat()..15f,
            steps = (15 - inteligenciaMinima)
        )

        // Sabedoria
        Text(text = "Sabedoria: ${sabedoria.value}")
        Slider(
            value = sabedoria.value.toFloat(),
            onValueChange = { newValue -> alocarPontos(sabedoria, newValue.toInt(), sabedoriaMinima) },
            valueRange = sabedoriaMinima.toFloat()..15f,
            steps = (15 - sabedoriaMinima)
        )

        // Carisma
        Text(text = "Carisma: ${carisma.value}")
        Slider(
            value = carisma.value.toFloat(),
            onValueChange = { newValue -> alocarPontos(carisma, newValue.toInt(), carismaMinima) },
            valueRange = carismaMinima.toFloat()..15f,
            steps = (15 - carismaMinima)
        )

        Button(onClick = {
            navController.navigate("resultados/$raca/${forca.value}/${destreza.value}/${constituicao.value}/${inteligencia.value}/${sabedoria.value}/${carisma.value}")
        }) {
            Text(text = "Confirmar Distribuição")
        }
    }
}


