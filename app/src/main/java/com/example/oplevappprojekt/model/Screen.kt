package com.example.oplevappprojekt.model

sealed class Screen(val route: String){
    object CreateTrip : Screen(route = "create_trip")
    object Trips : Screen(route = "trips_")
}
