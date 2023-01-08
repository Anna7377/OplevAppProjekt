package com.example.oplevappprojekt


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.oplevappprojekt.ViewModel.*
import com.example.oplevappprojekt.sites.*
import com.example.oplevappprojekt.ui.theme.OplevAppProjektTheme


class MainActivity : ComponentActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OplevApp()
        }
    }
}

// S215722 & s215718 & s213370
@Composable
fun OplevApp(){
    OplevAppProjektTheme {
        val journeyviewmodel = Journeysviewmodel()
        val authviewmodel = AuthViewModel()
        val categoryviewmodel = CategoryViewModel()
        val ideaviewmodel = Ideaviewmodel()
        val startRoute = "start"
        val mainroute = "main"
        val profile = "profile"
        val loginRoute="logIn"
        val signupRoute="signUp"
        val createIdea="Create idea"
        val idearoute="idea"
        val inspirationroute = "inspiration"
        val createroute="create"
        val changepassword="password"
        val createcategory="createcategory"
        val categorypage = "categorypage"
        val navigationController = rememberNavController()
    /* must be changed such that the startroute is defined by whether the user is logged in or not */
        NavHost(navController = navigationController,
            modifier = Modifier.fillMaxSize(),
            startDestination = startRoute
            ) {
            composable(route = startRoute) {
                    StartPage(navigate = { navigationController.navigate(loginRoute) })}
            composable(route=loginRoute){
                LoginPage(navigation = {navigationController.navigate(signupRoute)}, viewModel = AuthViewModel(), navMain = {navigationController.navigate(mainroute)})
            }
            composable(route=signupRoute){
                SignUpPage(viewModel = authviewmodel, navigation = {navigationController.navigate(loginRoute)},
                navMain = {navigationController.navigate(mainroute)}, Auth())
            }
            composable(route=mainroute){
                MainPage(navController = navigationController, navigationInsp = {navigationController.navigate(inspirationroute)},
                   navCreate = {navigationController.navigate(createroute)},
                    navProfile = {navigationController.navigate(profile)},
                    navIdeas = {navigationController.navigate(categorypage)},
                viewModel = journeyviewmodel)
            }
            composable(route=inspirationroute){
                Inspiration(navMain = {navigationController.navigate(mainroute)}, navProfile = {navigationController.navigate(profile)})
            }
            composable(route=createroute){
                Trip(navMain = {navigationController.navigate(mainroute)}, viewModel = journeyviewmodel)
            }
            composable(route=profile){
                UserProfile(navigationInspo = {navigationController.navigate(inspirationroute)},
                    navMain = {navigationController.navigate(mainroute)},
                    viewModel = authviewmodel,
                    navStart = {navigationController.navigate(startRoute)},
                    navChange = {navigationController.navigate(changepassword)})
            }
            composable(route=idearoute
            ) {
                MyJourneyPage(navCreate = {navigationController.navigate(createIdea)},
                   navIdeas = {navigationController.navigate(idearoute)},
                    navProfile = {navigationController.navigate(inspirationroute)},
                    navigationInsp = {navigationController.navigate(inspirationroute)},
                    viewModel = ideaviewmodel)
            }
            composable(route=createIdea){
                CreateIdea(navIdeas = {navigationController.navigate(idearoute)})
            }
            composable(route=changepassword){
                Password(
                    viewModel = AuthViewModel(),
                    navigation = { navigationController.navigate(profile)},
                )
            }
            composable(route=createcategory){
                CreateCategory(navCategories ={navigationController.navigate(categorypage)})

            }
            composable(route=categorypage){
                CategoryPage(navCreate = {navigationController.navigate(createcategory)},
                navCategories = {navigationController.navigate(categorypage)},
                navigationInsp = {navigationController.navigate(inspirationroute)},
                navProfile = {navigationController.navigate(inspirationroute)},
                    navIdeas = {navigationController.navigate(idearoute)}, viewModel = categoryviewmodel)
            }
    }
}}
