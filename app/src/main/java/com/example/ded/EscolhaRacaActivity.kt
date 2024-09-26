package com.example.ded

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class EscolhaRacaActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            EscolhaRacaScreen(navController)
        }
    }
}

@Composable
fun EscolhaRacaScreen(navController: NavHostController) {
    val racas = listOf(
        "AltoElfo", "Anao", "AnaoColina", "AnaoMontanha",
        "Draconato", "Drow", "Elfo", "ElfoFloresta",
        "Gnomo", "GnomoFloresta", "GnomoRochas",
        "Halfling", "HalflingPesLeves", "HalflingRobusto",
        "Humano", "MeioElfo", "MeioOrc", "Tiefling"
    )

    var dialogOpen by remember { mutableStateOf(false) }
    var selectedRaca by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Escolha sua Raça",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Botão para abrir o diálogo de seleção
        Button(
            onClick = { dialogOpen = true },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(text = selectedRaca ?: "Selecione uma raça")
        }

        // Botão para confirmar a seleção e navegar
        Button(
            onClick = {
                selectedRaca?.let { navController.navigate("distribuicaoPontos/$it") }
            },
            enabled = selectedRaca != null,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Confirmar Raça")
        }
    }

    // Diálogo para seleção de raça
    if (dialogOpen) {
        AlertDialog(
            onDismissRequest = { dialogOpen = false },
            title = { Text("Selecione uma Raça") },
            text = {
                LazyColumn {
                    items(racas) { raca ->
                        Text(
                            text = raca,
                            modifier = Modifier
                                .clickable {
                                    selectedRaca = raca
                                    dialogOpen = false
                                }
                                .padding(16.dp)
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { dialogOpen = false },
                    enabled = selectedRaca != null
                ) {
                    Text("Fechar")
                }
            }
        )
    }
}