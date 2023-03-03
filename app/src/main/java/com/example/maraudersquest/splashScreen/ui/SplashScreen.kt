package com.example.maraudersquest.splashScreen.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.maraudersquest.R
import com.example.maraudersquest.navegacion.AppScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    LaunchedEffect(key1 = true){
        delay(5000)
        navController.popBackStack()
        navController.navigate(AppScreens.Login.ruta)
    }


    Splash()
}

@Composable
fun Splash() {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(painter = painterResource(R.drawable.logotorrija),
            contentDescription = "Logo TorrijaTeam",
            Modifier.size(250.dp, 250.dp))

        Text(text = "Developed by Torrija Team",
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.padding(top = 20.dp)
        )

    }
}