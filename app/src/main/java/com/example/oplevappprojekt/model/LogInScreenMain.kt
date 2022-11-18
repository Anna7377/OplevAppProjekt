package com.example.oplevappprojekt.model

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.oplevappprojekt.sites.LoginPage

// s215718 & s213370

@Composable
    fun LogInScreenMain(){
        val navController = rememberNavController()

    NavHost(navController = navController, startDestinantion = Routes.LogIn.route){
        composable(Routes.LogIn.route){
            LoginPage(navController = navController)
        }
    }
}