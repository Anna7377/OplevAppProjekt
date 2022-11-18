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
import com.example.oplevappprojekt.model.LogInScreenMain
import com.example.oplevappprojekt.sites.LoginPage
import com.example.oplevappprojekt.sites.ScrollableList
import com.example.oplevappprojekt.sites.StartPage
import com.example.oplevappprojekt.ui.theme.OplevAppProjektTheme

class MainActivity : ComponentActivity() {
    private val viewModel=JourneyViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
        LogInScreenMain()
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
        val inspirationroute = "inspiration"
        val state = viewModel.uiState.collectAsState()
        val navigationController = rememberNavController()
    /* must be changed such that the startroute is defined by whether the user is logged in or not */
        NavHost(navController = navigationController,
            modifier = Modifier.fillMaxSize(),
            startDestination = startRoute) {
            composable(route = startRoute) {
                StartPage(navigate={navigationController.navigate(loginRoute)})
            }
            composable(route=loginRoute){
                LoginPage(viewModel=AuthViewModel)
            }
    }
}}
@Preview(showBackground = true)
@Composable
fun DefaultPreview(){

}
