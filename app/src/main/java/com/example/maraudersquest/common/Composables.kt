package com.example.maraudersquest.common

import android.media.MediaPlayer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.maraudersquest.MainActivity
import com.example.maraudersquest.R
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.firestore.FirebaseFirestore

//función para desloguear desde la cuenta de Google
@Composable
fun logOut(){
    val activity = LocalContext.current as MainActivity
    val gso: GoogleSignInOptions =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    val client = GoogleSignIn.getClient(activity, gso)
    Auth.GoogleSignInApi.signOut(client.asGoogleApiClient())
}

//función para reproducir la canción de fondo
@Composable
fun PlayAudio(){

    val context = LocalContext.current
    val mp : MediaPlayer = MediaPlayer.create(context, R.raw.overture)

    mp.start()

    mp.isLooping = true

}