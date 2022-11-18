package com.example.oplevappprojekt.ViewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.oplevappprojekt.Authentication.AuthRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oplevappprojekt.model.Journey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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

class AuthViewModel(private val authRepository : AuthRepository = AuthRepository()) : ViewModel() {

    private val _uiState = MutableStateFlow(Journey())
    val uiState = _uiState.asStateFlow()

    val currentUser = authRepository.currentUser

    val isLoggedIn: Boolean get() = authRepository.isloggedIn()

    var AuthUIstate by mutableStateOf(Auth())
        private set

    fun onNewMailChange(mail: String){
AuthUIstate=AuthUIstate.copy(newMail = mail)
    }
    fun onNewPassChange(pass: String){
AuthUIstate=AuthUIstate.copy(newPass = pass)
    }

   fun onConfPassChange(pass: String){
       AuthUIstate=AuthUIstate.copy(confPass=pass)
   }

    fun IsChecked(check: Boolean){
        AuthUIstate=AuthUIstate.copy(GDPRcheck = check)
    }

    fun tempCreateUser(mail: String, pass: String, confPass: String, GDPR: Boolean){
        if(mail.isEmpty() || pass.isEmpty() || confPass.isEmpty()){

        }
        if(!AuthUIstate.GDPRcheck){

        }
        if(confPass!=pass){

        }
    }

    fun createUser(context: Context) = viewModelScope.launch() {
        try {
            if (AuthUIstate.newPass.isEmpty() ||
                AuthUIstate.newMail.isEmpty() ||
                AuthUIstate.confPass.isEmpty()
            ) {
                throw IllegalArgumentException("Please fill out all fields")
            }
            if(!AuthUIstate.GDPRcheck){
                throw IllegalArgumentException("You must accept the GDPR rules")
            }
            AuthUIstate = AuthUIstate.copy(isLoading = true)
            if (AuthUIstate.newPass != AuthUIstate.confPass) {
                throw IllegalArgumentException("Passwords do not match")
            }
            AuthUIstate = AuthUIstate.copy(signUpError = null)
            authRepository.createUser(AuthUIstate.newMail, AuthUIstate.newPass)
            { isSuccessful ->
                if (isSuccessful) {
                    Toast.makeText(
                        context,
                        "success Login",
                        Toast.LENGTH_SHORT
                    ).show()
                    AuthUIstate = AuthUIstate.copy(isSuccessLogin = true)
                } else {
                    Toast.makeText(
                        context,
                        "Failed Login",
                        Toast.LENGTH_SHORT
                    ).show()
                    AuthUIstate = AuthUIstate.copy(isSuccessLogin = false)
                }

            }
        }
        catch (e: Exception) {
            AuthUIstate = AuthUIstate.copy(signUpError = e.localizedMessage)
            e.printStackTrace()
        } finally {
            AuthUIstate = AuthUIstate.copy(isLoading = false)
        }
    }
}



