package com.example.oplevappprojekt.ViewModel

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update



//s215722
data class Auth(
    val mail:String="_",
    val password:String="",
    val userName:String="",
    val isLoggedIn:Boolean=false
)

class AuthViewModel:ViewModel(){
    private lateinit var auth:FirebaseAuth
    private val _uiState = mutableStateOf(Auth())
    val uiState: State<Auth> = _uiState

    fun SignUp(email: String, password: String, confPass: String, baseContext: Context, activity: Activity) {
        // [START create_user_with_email]
        if (confPass != password) {
            Toast.makeText(baseContext, "Passwordsdonotmatch", Toast.LENGTH_SHORT).show()
        }
        else{

        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    _uiState.value=_uiState.value.copy(mail=email)
                    val user = Firebase.auth.currentUser
                    updateUI( true)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(false)
                }
                }
            }
        // [END create_user_with_email]
    }

    fun SignIn(email: String, password: String, baseContext: Context, activity: Activity) {
        // [START sign_in_with_email]
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    _uiState.value = _uiState.value.copy(mail=email)
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = Firebase.auth.currentUser
                    updateUI(true)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI( false)
                }
            }
        // [END sign_in_with_email]
    }

    fun updateUI(isSuccessful : Boolean) {
        _uiState.value = _uiState.value.copy(isLoggedIn = isSuccessful)
    }


    fun deleteUser(){
        auth.currentUser?.delete()
    }
    fun logout(){
        FirebaseAuth.getInstance().signOut()
        System.out.println("In Logout")
        _uiState.value = _uiState.value.copy(isLoggedIn = false)
    }

}

/*
package com.example.oplevappprojekt.ViewModel

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


//s215722
data class Auth(
val mail:String="",
val password:String="",
val userName:String="",
val isLoggedIn:Boolean=false,
val GDPRcheck:Boolean=false,
var user : FirebaseUser? = null
)

class AuthViewModel:ViewModel(){
    private lateinit var auth:FirebaseAuth
    private val _uiState = mutableStateOf(Auth())
    val uiState: State<Auth> = _uiState

    fun SignUp(mail:String, pass:String,confPass:String, name: String, context: Context, activity: Activity) {
        System.out.println(mail + pass + confPass)
        if (mail.isEmpty() || pass.isEmpty() || confPass.isEmpty()) {
            Toast.makeText(context, "Pleasefilloutallcells", Toast.LENGTH_SHORT).show()
        } else {
            if (confPass != pass) {
                Toast.makeText(context, "Passwordsdonotmatch", Toast.LENGTH_SHORT).show()
            } else {
                Firebase.auth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(activity)
                { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = Firebase.auth.currentUser
                        updateUI(user, true)
                        _uiState.value = _uiState.value.copy(userName = name, mail=mail)
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            context, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null, false)
                    }
                }
            }
        }
    }

    fun SignIn(mail: String, pass: String, context: Context, activity: Activity){
        Firebase.auth.signInWithEmailAndPassword(mail, pass)
            .addOnCompleteListener(activity){ task ->
            if(task.isSuccessful){
            Log.d(TAG, "SignInSuccess")
            val user = Firebase.auth.currentUser
            updateUI(user, true)
                _uiState.value = _uiState.value.copy(mail=mail)
                System.out.println(uiState.value.isLoggedIn)
        } else {
                Log.w(TAG, "SignInFail")
            Toast.makeText(
                context, "Authentication failed.",
                Toast.LENGTH_SHORT
            ).show()
        }
                updateUI(null, false)
        }
    }

    fun updateUI(user: FirebaseUser?, isSuccessful : Boolean) {
        _uiState.value = _uiState.value.copy(isLoggedIn = isSuccessful, user = user)}

    fun deleteUser(){
        auth.currentUser?.delete()
    }

}

*/