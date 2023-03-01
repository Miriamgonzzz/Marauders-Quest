package com.example.maraudersquest.navegacion

sealed class AppScreens(val ruta: String){
    object SplashScreen: AppScreens("com/example/maraudersquest/splashScreen")
    object Login: AppScreens("com/example/maraudersquest/login")
    object Index: AppScreens("com/example/maraudersquest/index")
    object Ciudades: AppScreens("com/example/maraudersquest/ciudades")
    object Rutas: AppScreens("com/example/maraudersquest/rutas")
    object RutaElegida: AppScreens("com/example/maraudersquest/rutaElegida")
    object Baul: AppScreens("com/example/maraudersquest/baul")
    object Historial: AppScreens("com/example/maraudersquest/historial")
}
