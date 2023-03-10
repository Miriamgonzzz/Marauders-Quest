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
    private val name = MutableLiveData("")
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
                //en la variable auth se guardan los datos del usuario que actualmente est?? logueado
                val auth = FirebaseAuth.getInstance()
                val credential = GoogleAuthProvider.getCredential(token, null)
                auth.signInWithCredential(credential)
                    .addOnCompleteListener{
                        if (it.isSuccessful) {
                            val user = auth.currentUser
                            name.postValue(user?.displayName)
                            email.postValue(user?.email)
                            loggedUser.postValue(auth.currentUser)
                            val databaseInsertUserMail = user?.email
                            val databaseInsertUserName = user?.displayName

                            if (databaseInsertUserMail != null && databaseInsertUserName != null) {
                                createUser(databaseInsertUserMail, databaseInsertUserName)
                            }

                        }else {
                            hasGoogleError.postValue(true)
                            googleError.postValue("Ha ocurrido un error al iniciar sesi??n")
                        }
                        isLoading.postValue(false)
                    }
            }
        } catch (e: ApiException) {
            hasGoogleError.postValue(true)
            googleError.postValue("Ha ocurrido alg??n error")
            isLoading.postValue(false)
            e.message?.let { Log.d("Login", "error: "+it) }

        }
    }
    //funci??n para insertar en el FireBase de la aplicaci??n el usuario por medio del campo ??nico
    //de email
    private fun createUser(userMail: String, userName: String) {

        val userData = hashMapOf("email" to userMail, "name" to userName)

        db.collection(usersCollectionName)
            .document(userMail)
            .set(userData, SetOptions.merge())
            .addOnSuccessListener {
                //aqu?? se pondr??a un mensaje o popUp de bienvenida
            }
            .addOnFailureListener {
                //aqu?? si ha fallado algo durante el registro con Google
            }
    }

    //funci??n para obtener el usuario de Firebase de la aplicaci??n
    fun getUser(user: String){

        db.collection(usersCollectionName)
            .document(user)
            .get()
            .addOnSuccessListener {

            }
    }

}