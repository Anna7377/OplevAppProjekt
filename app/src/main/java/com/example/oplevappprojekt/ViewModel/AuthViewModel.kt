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
        _uiState.value = _uiState.value.copy(isLoggedIn = isSuccessful)}

    fun deleteUser(){
        Firebase.auth.currentUser!!.delete()
    }

}

