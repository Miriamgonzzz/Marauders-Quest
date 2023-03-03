package com.example.maraudersquest.index.ui

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.maraudersquest.R

@Composable
fun Index(navController: NavController){

        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(R.drawable.background)
                )
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo_MaraudersQuest",
                modifier = Modifier.size(250.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))

            IconButton(onClick = { navController.navigate("rutaElegida")}) {
                Image(
                    painter = painterResource(R.drawable.nueva_partida),
                    contentDescription = "Nuevapartida",
                    modifier = Modifier.size(200.dp)
                    )
            }

            Spacer(modifier = Modifier.size(8.dp))

            IconButton(onClick = { navController.navigate("")}) {
                Image(
                    painter = painterResource(R.drawable.continuar),
                    contentDescription = "ContinuarPartida",
                    modifier = Modifier.size(200.dp))
            }

            Spacer(modifier = Modifier.size(8.dp))

            IconButton(onClick = { navController.navigate("")}) {
                Image(
                    painter = painterResource(R.drawable.salir),
                    contentDescription = "Salir",
                    modifier = Modifier.size(200.dp))
            }
        }
}