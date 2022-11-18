package com.example.oplevappprojekt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.oplevappprojekt.ViewModel.AuthViewModel
import com.example.oplevappprojekt.ViewModel.JourneyViewModel

import com.example.oplevappprojekt.ui.theme.OplevAppProjektTheme

class MainActivity : ComponentActivity() {
    private val viewModel=JourneyViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
        }
    }
}

// S215722
@Composable
fun OplevApp(viewModel: AuthViewModel){
    OplevAppProjektTheme {
        val startRoute = "start"
        val mainroute = "main"
        val loginRoute="logIn"
        val signupRoute="signUp"
        val inspirationroute = "inspiration"
        val state = viewModel.uiState.collectAsState()
        val navigationController = rememberNavController()
    /* must be changed such that the startroute is defined by whether the user is logged in or not */
        NavHost(navController = navigationController,
            modifier = Modifier.fillMaxSize(),
            startDestination = startRoute) {
            composable(route = startRoute) {
                if(!viewModel.isLoggedIn) {
                    StartPage(navigate = { navigationController.navigate(loginRoute) })
                }
                else{
                    MainPage(navigationInsp = {navigationController.navigate(inspirationroute)})
                }
            }
            composable(route=loginRoute){
                LoginPage(navigation = {navigationController.navigate(signupRoute)})
            }
            composable(route=signupRoute){
                SignUpPage(viewModel = AuthViewModel(), navigation = {navigationController.navigate(loginRoute)},
                navMain = {navigationController.navigate(mainroute)})
            }
            composable(route=mainroute){
                MainPage(navigationInsp = {navigationController.navigate(inspirationroute)})
            }
            composable(route=inspirationroute){
                Inspiration()
            }
    }


}
