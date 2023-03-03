package com.example.maraudersquest.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.maraudersquest.baul.ui.Baul
import com.example.maraudersquest.ciudades.ui.Ciudades
import com.example.maraudersquest.historial.ui.Historial
import com.example.maraudersquest.index.ui.Index
import com.example.maraudersquest.login.ui.Login
import com.example.maraudersquest.rutaElegida.ui.RutaElegida
import com.example.maraudersquest.rutas.ui.Rutas
import com.example.maraudersquest.splashScreen.ui.SplashScreen


@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()

    NavHost(navController = navigationController, startDestination = AppScreens.SplashScreen.ruta)

    {
        composable(AppScreens.SplashScreen.ruta) { SplashScreen(navigationController) }
        composable(AppScreens.Login.ruta) { Login(navigationController) }
        composable(AppScreens.Index.ruta) { Index(navigationController) }
        composable(AppScreens.Ciudades.ruta) { Ciudades(navigationController) }
        composable(AppScreens.Rutas.ruta) { Rutas(navigationController) }
        composable(AppScreens.RutaElegida.ruta) { RutaElegida(navigationController) }
        composable(AppScreens.Baul.ruta) { Baul(navigationController) }
        composable(AppScreens.Historial.ruta) { Historial(navigationController) }
    }
}