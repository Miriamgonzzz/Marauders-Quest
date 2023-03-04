package com.example.maraudersquest.ciudades.ui

import androidx.compose.foundation.layout.Column
import com.example.maraudersquest.R
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.maraudersquest.MainActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

@Composable
fun Ciudades(navController: NavController){
    val activity = LocalContext.current as MainActivity

    Column() {
        Text(text = "Estás en la página Ciudades ")
        Button(
            onClick = {
                val gso: GoogleSignInOptions =
                    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(activity.getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build()
                val client = GoogleSignIn.getClient(activity, gso)
                Auth.GoogleSignInApi.signOut(client.asGoogleApiClient())
                navController.navigate("login")
            },
            modifier = Modifier.width(250.dp)) {
            Text(text = "Cerrar sesión")
        }
        
    }
}