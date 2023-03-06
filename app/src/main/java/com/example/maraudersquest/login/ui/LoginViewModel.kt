package com.example.maraudersquest.login.ui

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.maraudersquest.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class LoginViewModel() : ViewModel() {

    private val isLoading = MutableLiveData(false)
    private val logged = MutableLiveData(false)
    private val hasGoogleError = MutableLiveData(false)
    private val googleError = MutableLiveData("")
    private val email = MutableLiveData("")
    private val loggedUser = MutableLiveData(FirebaseAuth.getInstance().currentUser)
    fun loggedUser(): MutableLiveData<FirebaseUser?> = loggedUser
    fun isLoading(): LiveData<Boolean> = isLoading
    fun logged(): LiveData<Boolean> = logged
    fun googleError(): LiveData<String> = googleError
    fun hasGoogleError(): LiveData<Boolean> = hasGoogleError
    private val db = FirebaseFirestore.getInstance()
    private val usersCollectionName = "users"

    fun logIn() {
        logged.postValue(true)
    }

    fun logInWithGoogle(activity: Activity) {
        logged.postValue(false)
        loggedUser.postValue(null)
        isLoading.postValue(true)
        val gso: GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        // Build a GoogleSignInClient with the options specified by gso.
        val client = GoogleSignIn.getClient(activity, gso)
        val signInIntent: Intent = client.signInIntent
        activity.startActivityForResult(signInIntent, 1)
    }

    fun finishLogIn(task: Task<GoogleSignInAccount>) {
        try {
            //en la variable account guardamos todos los datos de Google del usuario
            val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)

            account?.idToken?.let { token ->
                //en la variable auth se guardan los datos del usuario que actualmente está logueado
                val auth = FirebaseAuth.getInstance()
                val credential = GoogleAuthProvider.getCredential(token, null)
                auth.signInWithCredential(credential)
                    .addOnCompleteListener{
                        if (it.isSuccessful) {
                            val user = auth.currentUser
                            email.postValue(user?.email)
                            loggedUser.postValue(auth.currentUser)
                            val databaseInsertUser = user?.email

                            if (databaseInsertUser != null) {
                                createUser(databaseInsertUser)
                            }

                        }else {
                            hasGoogleError.postValue(true)
                            googleError.postValue("Ha ocurrido un error al iniciar sesión")
                        }
                        isLoading.postValue(false)
                    }
            }
        } catch (e: ApiException) {
            hasGoogleError.postValue(true)
            googleError.postValue("Ha ocurrido algún error")
            isLoading.postValue(false)
            e.message?.let { Log.d("Login", "error: "+it) }

        }
    }
    //función para insertar en el FireBase de la aplicación el usuario por medio del campo único
    //de email
    private fun createUser(user: String) {

        val userData = hashMapOf("email" to user)

        db.collection(usersCollectionName)
            .document(user)
            .set(userData, SetOptions.merge())
            .addOnSuccessListener {
                //aquí se pondría un mensaje o popUp de bienvenida
            }
            .addOnFailureListener {
                //aquí si ha fallado algo durante el registro con Google
            }
    }

    //función para obtener el usuario de Firebase de la aplicación
    fun getUser(user: String){

        db.collection(usersCollectionName)
            .document(user)
            .get()
            .addOnSuccessListener {

            }
    }

}