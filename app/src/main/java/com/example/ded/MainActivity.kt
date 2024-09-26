package com.example.ded

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Arrangement
import com.example.ded.ui.theme.DeDTheme

class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                DeDTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        val navController = rememberNavController()

                        NavHost(
                            navController = navController,
                            startDestination = "greeting",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("greeting") { GreetingScreen(navController) }
                            composable("escolhaRaca") { EscolhaRacaScreen(navController) }
                            composable("distribuicaoPontos/{raca}") { backStackEntry ->
                                val raca = backStackEntry.arguments?.getString("raca")
                                DistribuicaoPontosScreen(navController, raca)
                            }
                            composable("resultados/{raca}/{forca}/{destreza}/{constituicao}/{inteligencia}/{sabedoria}/{carisma}") { backStackEntry ->
                                val raca = backStackEntry.arguments?.getString("raca")
                                val forca = backStackEntry.arguments?.getString("forca")?.toInt() ?: 8
                                val destreza = backStackEntry.arguments?.getString("destreza")?.toInt() ?: 8
                                val constituicao = backStackEntry.arguments?.getString("constituicao")?.toInt() ?: 8
                                val inteligencia = backStackEntry.arguments?.getString("inteligencia")?.toInt() ?: 8
                                val sabedoria = backStackEntry.arguments?.getString("sabedoria")?.toInt() ?: 8
                                val carisma = backStackEntry.arguments?.getString("carisma")?.toInt() ?: 8
                                ResultadosScreen(navController, raca, forca, destreza, constituicao, inteligencia, sabedoria, carisma)
                            }
                        }
                    }
                }
            }
        }
    }


    @Composable
fun GreetingScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Bem-vindo ao Criador de Personagem!")
        Button(onClick = { navController.navigate("escolhaRaca") }) {
            Text(text = "Escolher Raça")
        }
    }
}

@Preview
@Composable
fun PreviewEscolhaRacaScreen() {
    EscolhaRacaScreen(rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DeDTheme {
        GreetingScreen(rememberNavController())
    }
}
