package com.example.oplevappprojekt.ViewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import com.example.oplevappprojekt.Authentication.AuthRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.oplevappprojekt.model.Journey
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


// s215722
data class Auth (
    val mail: String ="",
    val password: String="",
    val userName: String="",
   val newMail: String="",
    val newPass: String="",
    val confPass: String="",
    val isLoggedIn: Boolean=false,
    val isLoading: Boolean = false,
    val isSuccessLogin: Boolean = false,
    val signUpError: String? = null,
    val loginError: String? = null,
    val GDPRcheck: Boolean = false
)

class AuthViewModel() {


    private val _uiState = MutableStateFlow(Auth())
    val uiState = _uiState.asStateFlow()

    suspend fun linkAccount(email: String, password: String) {

    }

    fun updateCredentials(mail: String, pass: String, confPass: String, check: Boolean){
        _uiState.update { it.copy(newMail=mail, newPass = pass, confPass = confPass, GDPRcheck = check ) }
        SignUp(mail, pass, confPass)
    }
    fun SignUp(mail: String, pass: String, confPass: String)  {
        System.out.println(mail + pass + confPass)

        Firebase.auth.createUserWithEmailAndPassword(mail, pass)
        }  }



