package com.example.oplevappprojekt.model

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.oplevappprojekt.CreateTrip
import com.example.oplevappprojekt.sites.FinalTrip

@Composable
fun SetUpNavigation(
    navController: NavHostController
){
    NavHost(navController = navController,
            startDestination = Screen.CreateTrip.route
    ) {
        composable(
            route = Screen.CreateTrip.route
        ) {
            CreateTrip(navController = navController)
        }
        composable(
            route = Screen.Trips.route
        ) {
            FinalTrip()
        }
    }
}
