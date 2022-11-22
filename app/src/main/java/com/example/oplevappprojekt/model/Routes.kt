package com.example.oplevappprojekt.model

// s215718 & s213370

sealed class Routes (val route: String){
    object LogIn : Routes("LogIn")
}