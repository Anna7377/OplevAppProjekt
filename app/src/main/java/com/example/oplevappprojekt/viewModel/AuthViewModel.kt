package com.example.oplevappprojekt.viewModel

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.oplevappprojekt.data.AuthRepository
import kotlinx.coroutines.runBlocking

data class Auth(
    val mail:String="_",
    val password:String="",
    val userName:String="",
    val isLoggedIn:Boolean=false)

class AuthViewModel{
  //  private val _uiState = mutableStateOf(Auth())
    // val uiState: State<Auth> = _uiState

    val authrepo = AuthRepository()

    fun SignUp(email: String,
               password: String,
               confPass: String,
               baseContext: Context,
               activity: Activity,
               name: String){
        runBlocking {
            authrepo.SignUp(
                email = email,
                password = password,
                confPass = confPass,
                baseContext = baseContext,
                activity = activity,
                name = name
            )
        }
    }
    fun SignIn(email: String, password: String, baseContext: Context, activity: Activity){
        runBlocking {
            authrepo.SignIn(email = email, password = password, baseContext = baseContext,activity =activity)
        }
    }

    fun deleteUser(ID : String){
       runBlocking {
           authrepo.deleteUser(
               ID = ID
           )
       }
    }
    fun logout(){
        runBlocking{
            authrepo.logout()
        }
    }
    fun changePassword(currentPass: String, newPass: String, confirmNewPass: String){
        runBlocking {
            authrepo.changePassword(currentPass=currentPass, newPass=newPass,confirmNewPass=confirmNewPass)
        }
    }
    fun emailVerification(){
        runBlocking {
            authrepo.emailVerification()
        }
    }
}