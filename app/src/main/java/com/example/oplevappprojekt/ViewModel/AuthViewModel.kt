package com.example.oplevappprojekt.ViewModel

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


///s215722
data class Auth(
val mail:String="",
val password:String="",
val userName:String="",
val newMail:String="",
val newPass:String="",
val confPass:String="",
val isLoggedIn:Boolean=false,
val isLoading:Boolean=false,
val isSuccessLogin:Boolean=false,
val signUpError:String?=null,
val loginError:String?=null,
val GDPRcheck:Boolean=false
)

class AuthViewModel():Activity(){
    private lateinit var auth:FirebaseAuth
    private val _uiState=MutableStateFlow(Auth())
    val uiState=_uiState.asStateFlow()

    public override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        auth=Firebase.auth
    }

    public override fun onStart(){
        super.onStart()
        val currentUser=auth.currentUser
    }

var loggedIn: Boolean = false
    fun SignUp(mail:String, pass:String,confPass:String) {
        System.out.println(mail + pass + confPass)
        if (mail.isEmpty() || pass.isEmpty() || confPass.isEmpty()) {
            Toast.makeText(this, "Pleasefilloutallcells", Toast.LENGTH_SHORT).show()
        } else {
            if (confPass != pass) {
                Toast.makeText(this, "Passwordsdonotmatch", Toast.LENGTH_SHORT).show()
            } else {
                Firebase.auth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(this)
                { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    }
                }
            }
        }
    }
    fun TryLogIn(mail: String, pass: String){
        _uiState.update{
            it.copy(
                mail=mail,
                password =pass,
            )
        }
        SignIn(mail, pass)
    }
    fun SignIn(mail: String, pass: String){
        Firebase.auth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(this){
                task -> if(task.isSuccessful){
            Log.d(TAG, "SignInSuccess")
            val user = auth.currentUser
            _uiState.update { it.copy(isLoggedIn = true, mail=mail, password = pass) }
            loggedIn = true
        }
            else {
                Log.w(TAG, "SignInFail")
        }
        }
    }
    fun isLoggedin() : Boolean {
        return loggedIn
    }
}

