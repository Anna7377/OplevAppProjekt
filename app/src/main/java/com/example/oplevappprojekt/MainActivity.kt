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
import com.example.oplevappprojekt.ViewModel.Auth
import com.example.oplevappprojekt.ViewModel.AuthViewModel
import com.example.oplevappprojekt.ViewModel.JourneyViewModel
import com.example.oplevappprojekt.ViewModel.MyJourneysViewModel
import com.example.oplevappprojekt.data.HardcodedJourneysRepository


import com.example.oplevappprojekt.data.JourneyRepository

import com.example.oplevappprojekt.model.Journey
import com.example.oplevappprojekt.sites.*

import com.example.oplevappprojekt.ui.theme.OplevAppProjektTheme

import com.google.firebase.firestore.ktx.firestore


import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

import java.util.*


class MainActivity : ComponentActivity() {



    private val repository = JourneyRepository(firestore = Firebase.firestore)

    override fun onCreate(savedInstanceState: Bundle?) {

        runBlocking {
            repository.addJourney(journey = com.example.oplevappprojekt.data.Journey(
                country = "Japan",
                tripName = "AsianTrip"
            ))
        }

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
        val profile = "profile"
        val loginRoute="logIn"
        val signupRoute="signUp"
        val createIdea="Create idea"
        val idearoute="idea"
        val inspirationroute = "inspiration"
        val createroute="create"
        val state = viewModel.uiState.value
        val navigationController = rememberNavController()
    /* must be changed such that the startroute is defined by whether the user is logged in or not */
        NavHost(navController = navigationController,
            modifier = Modifier.fillMaxSize(),
            startDestination = loginRoute) {

            val repo = HardcodedJourneysRepository()
            composable(route = startRoute) {
                    StartPage(navigate = { navigationController.navigate(loginRoute) })
            }
            composable(route=loginRoute){
                LoginPage(navigation = {navigationController.navigate(signupRoute)}, viewModel = AuthViewModel(), navMain = {navigationController.navigate(mainroute)}, Auth())
            }
            composable(route=signupRoute){
                SignUpPage(viewModel = AuthViewModel(), navigation = {navigationController.navigate(loginRoute)},
                navMain = {navigationController.navigate(mainroute)}, Auth())
            }
            composable(route=mainroute){
                MainPage(navigationInsp = {navigationController.navigate(inspirationroute)},
                    MyJourneysViewModel(repo),
                   navCreate = {navigationController.navigate(createroute)}, navProfile = {navigationController.navigate(profile)}, navIdeas = {navigationController.navigate(idearoute)})
            }
            composable(route=inspirationroute){
                Inspiration(navMain = {navigationController.navigate(mainroute)}, navProfile = {navigationController.navigate(profile)})
            }
            composable(route=createroute){
                Trip(navMain = {navigationController.navigate(mainroute)}, viewModel = JourneyViewModel(repo))
            }
            composable(route=profile){
                UserProfile(navigationInspo = {navigationController.navigate(inspirationroute)}, navMain = {navigationController.navigate(mainroute)}, viewModel = AuthViewModel(), state= Auth(), navStart = {navigationController.navigate(startRoute)})
            }
            composable(route=idearoute){
                MyJourneyPage(navCreate = {navigationController.navigate(createIdea)}, MyJourneysViewModel())
            }
            composable(route=createIdea){
                CreateIdea(navIdeas = {navigationController.navigate(idearoute)})
            }
    }


}}
