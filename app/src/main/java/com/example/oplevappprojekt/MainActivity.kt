package com.example.oplevappprojekt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.oplevappprojekt.ViewModel.AuthViewModel
import com.example.oplevappprojekt.ViewModel.JourneyViewModel
import com.example.oplevappprojekt.ViewModel.MyJourneysViewModel
import com.example.oplevappprojekt.data.HardcodedJourneysRepository
import com.example.oplevappprojekt.sites.*

import com.example.oplevappprojekt.ui.theme.OplevAppProjektTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OplevApp(viewModel = AuthViewModel())
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
        val createroute="create"
        val state = viewModel.uiState.collectAsState()
        val navigationController = rememberNavController()
    /* must be changed such that the startroute is defined by whether the user is logged in or not */
        NavHost(navController = navigationController,
            modifier = Modifier.fillMaxSize(),
            startDestination = mainroute) {

            val repo = HardcodedJourneysRepository()
            composable(route = startRoute) {
                    StartPage(navigate = { navigationController.navigate(loginRoute) })
            }
            composable(route=loginRoute){
                LoginPage(navigation = {navigationController.navigate(signupRoute)}, viewModel = AuthViewModel(), navMain = {navigationController.navigate(mainroute)})
            }
            composable(route=signupRoute){
                SignUpPage(viewModel = AuthViewModel(), navigation = {navigationController.navigate(loginRoute)},
                navMain = {navigationController.navigate(mainroute)})
            }
            composable(route=mainroute){
                MainPage(navigationInsp = {navigationController.navigate(inspirationroute)},
                    MyJourneysViewModel(repo),
                   navCreate = {navigationController.navigate(createroute)})
            }
            composable(route=inspirationroute){
                Inspiration()
            }
            composable(route=createroute){
                Trip(JourneyViewModel(repo), navMain = {navigationController.navigate(mainroute)})
            }
    }


}}
