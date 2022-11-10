package com.example.oplevappprojekt

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class OplevUIState( val username: String = "",
                         val password: String ="",
                         val loggedIn: Boolean = false )

class ViewModel {
    private val _uiState = MutableStateFlow(OplevUIState())
    val uiState = _uiState.asStateFlow()
    fun SignUp(){

    }
    fun LogIn(){

    }
    fun logOut(){

    }
}