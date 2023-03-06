package com.example.maraudersquest.rutaElegida.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.maraudersquest.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@Composable
fun RutaElegida(navController: NavController) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val mapHeight = maxHeight * 0.85f // La imagen "map" ocupa el 85% de la pantalla
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(mapHeight)
        ) {
            // Agregamos el resto del contenido de la vista
            Image(
                painter = painterResource(R.drawable.map),
                contentDescription = "Logo_MaraudersQuest",
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(maxHeight - mapHeight)
                .align(Alignment.BottomCenter)
                .background(color = Color.Black)
        ) {
            // Agregamos el fondo y los botones del footer
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .heightIn(min = 56.dp)
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigate("baul") }) {
                    Image(
                        painter = painterResource(R.drawable.chest),
                        contentDescription = "Baul",
                        modifier = Modifier.size(60.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(onClick = { navController.navigate("historial") }) {
                    Image(
                        painter = painterResource(R.drawable.historial),
                        contentDescription = "Historial",
                        modifier = Modifier.size(60.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(onClick = { navController.navigate("") }) {
                    Image(
                        painter = painterResource(R.drawable.mute),
                        contentDescription = "Mute",
                        modifier = Modifier.size(60.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(onClick = { navController.navigate("index") }) {
                    Image(
                        painter = painterResource(R.drawable.salir_button),
                        contentDescription = "Volver",
                        modifier = Modifier.size(60.dp)
                    )
                }
            }
        }
    }
}