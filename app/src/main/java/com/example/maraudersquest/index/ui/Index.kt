package com.example.maraudersquest.index.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.maraudersquest.R
import com.google.firebase.auth.FirebaseAuth

val auth = FirebaseAuth.getInstance()
val usuarioLogueado = auth.currentUser
var correoUsuario = usuarioLogueado?.email
var nombreUsuario = usuarioLogueado?.displayName

@Composable
fun Index(navController: NavController){

        Column(

            modifier = Modifier
                .fillMaxSize()
                .border(width = 2.dp, color = Color.Gray)
                .paint(
                    painter = painterResource(R.drawable.background)
                )

        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo_MaraudersQuest",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(250.dp)
                    .padding(top = 15.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))

            IconButton(onClick = { navController.navigate("rutaElegida")},modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)) {
                Image(
                    painter = painterResource(R.drawable.nueva_partida),
                    contentDescription = "Nuevapartida",
                    modifier = Modifier.size(200.dp)
                    )
            }

            Spacer(modifier = Modifier.size(8.dp))

            IconButton(onClick = { navController.navigate("Ciudades")},modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)) {
                Image(
                    painter = painterResource(R.drawable.continuar),
                    contentDescription = "ContinuarPartida",
                    modifier = Modifier.size(200.dp)
            )
            }

            Spacer(modifier = Modifier.size(8.dp))

            IconButton(onClick = { navController.navigate("Ciudades")},modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)) {
                Image(
                    painter = painterResource(R.drawable.salir),
                    contentDescription = "Salir",
                    modifier = Modifier.size(200.dp)
            )
            }
        }
}