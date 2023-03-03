package com.example.maraudersquest.navegacion

sealed class AppScreens(val ruta: String){
    object SplashScreen: AppScreens("splashScreen")
    object Login: AppScreens("login")
    object Index: AppScreens("index")
    object Ciudades: AppScreens("ciudades")
    object Rutas: AppScreens("rutas")
    object RutaElegida: AppScreens("rutaElegida")
    object Baul: AppScreens("baul")
    object Historial: AppScreens("historial")
}
