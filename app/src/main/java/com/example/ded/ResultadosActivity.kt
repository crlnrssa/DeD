package com.example.ded

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class ResultadosActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            val raca = intent.getStringExtra("raca") ?: "Desconhecida"
            val forca = intent.getIntExtra("forca", 8)
            val destreza = intent.getIntExtra("destreza", 8)
            val constituicao = intent.getIntExtra("constituicao", 8)
            val inteligencia = intent.getIntExtra("inteligencia", 8)
            val sabedoria = intent.getIntExtra("sabedoria", 8)
            val carisma = intent.getIntExtra("carisma", 8)

            ResultadosScreen(navController, raca, forca, destreza, constituicao, inteligencia, sabedoria, carisma)
        }
    }
}

@Composable
fun ResultadosScreen(
    navController: NavHostController,
    raca: String?,
    forca: Int,
    destreza: Int,
    constituicao: Int,
    inteligencia: Int,
    sabedoria: Int,
    carisma: Int
) {
    // Função para calcular o modificador
    fun calcularModificador(valor: Int): Int {
        return (valor - 10) / 2
    }

    // Modificadores dos atributos
    val modificadorForca = calcularModificador(forca)
    val modificadorDestreza = calcularModificador(destreza)
    val modificadorConstituicao = calcularModificador(constituicao)
    val modificadorInteligencia = calcularModificador(inteligencia)
    val modificadorSabedoria = calcularModificador(sabedoria)
    val modificadorCarisma = calcularModificador(carisma)

    // Cálculo da vida com base na constituição
    val vida = 10 + modificadorConstituicao

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Resultados Finais", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)

        // Exibindo raça escolhida
        Text(text = "Raça escolhida: $raca", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(vertical = 8.dp))

        // Exibindo atributos e modificadores em caixas organizadas
        AtributosCard("Força", forca, modificadorForca)
        AtributosCard("Destreza", destreza, modificadorDestreza)
        AtributosCard("Constituição", constituicao, modificadorConstituicao)
        AtributosCard("Inteligência", inteligencia, modificadorInteligencia)
        AtributosCard("Sabedoria", sabedoria, modificadorSabedoria)
        AtributosCard("Carisma", carisma, modificadorCarisma)

        // Exibindo valor da vida
        Text(text = "Vida: $vida", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(vertical = 8.dp))

        Button(
            onClick = {
                navController.popBackStack("greeting", inclusive = false)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Voltar")
        }
    }
}

@Composable
fun AtributosCard(nome: String, valor: Int, modificador: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "$nome: $valor (Modificador: ${if (modificador >= 0) "+$modificador" else "$modificador"})", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
