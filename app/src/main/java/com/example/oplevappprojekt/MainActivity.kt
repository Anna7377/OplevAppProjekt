package com.example.oplevappprojekt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.oplevappprojekt.sites.ScrollableList
import com.example.oplevappprojekt.ui.theme.OplevAppProjektTheme

class MainActivity : ComponentActivity() {
    private val viewModel=ViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OplevApp(viewModel)
        }
    }
}

@Composable
fun OplevApp(viewModel: ViewModel){
    OplevAppProjektTheme {
        val startRoute = "start"
        val mainroute = "main"
        val inspirationroute = "inspiration"
        val state = viewModel.uiState.collectAsState()
        val navigationController = rememberNavController()
    /* must be changed such that the startroute is defined by whether the user is logged in or not */
        NavHost(navController = navigationController,
            modifier = Modifier.fillMaxSize(),
            startDestination = startRoute) {
            composable(route = startRoute) {
                ScrollableList()
            }
    }
}}

