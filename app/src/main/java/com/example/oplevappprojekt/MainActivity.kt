package com.example.oplevappprojekt


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.oplevappprojekt.ViewModel.*
import com.example.oplevappprojekt.ViewModel.Auth
import com.example.oplevappprojekt.ViewModel.AuthViewModel
import com.example.oplevappprojekt.ViewModel.Journeysviewmodel
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

// S215722
@Composable
fun OplevApp(){
    OplevAppProjektTheme {
        val journeyviewmodel = Journeysviewmodel()
        val colviewmodel = CollaboratorViewmodel()
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
        val createroute = "create"
        val createcategory = "create category"
        val categorypage = "categeory page"
        val changepassword = "password"
        val inviteroute = "invite"
        val opencategory = "open category"
        val delete = "delete"
        val editcategory = "edit category"
        val deletecategory = "delete category"
        val navigationController = rememberNavController()
    /* must be changed such that the startroute is defined by whether the user is logged in or not */
        NavHost(navController = navigationController,
            modifier = Modifier.fillMaxSize(),
            startDestination = startRoute
            ) {
            composable(route = startRoute) {
                StartPage(navigate = { navigationController.navigate(loginRoute) })
            }
            composable(route = loginRoute) {
                LoginPage(
                    navigation = { navigationController.navigate(signupRoute) },
                    viewModel = AuthViewModel(),
                    navMain = { navigationController.navigate(mainroute) })
            }
            composable(route = signupRoute) {
                SignUpPage(viewModel = authviewmodel,
                    navigation = { navigationController.navigate(loginRoute) },
                    navMain = { navigationController.navigate(mainroute) },
                    Auth()
                )
            }
            composable(route = inviteroute) {
                invite(viewmodel = colviewmodel)
            }
            composable(route = mainroute) {
                MainPage(navigationInsp = { navigationController.navigate(inspirationroute) },
                    navCreate = { navigationController.navigate(createroute) },
                    navProfile = { navigationController.navigate(profile) },
                    navInvite = { navigationController.navigate(inviteroute) },
                    navCategories = { navigationController.navigate(categorypage) },
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
                ideaviewmodel.uiState.value.currenttitle = categoryviewmodel.uiState.value.currenttitle
                ideaviewmodel.uiState.value.currentCategoryID = categoryviewmodel.uiState.value.currentCategoryID
                MyJourneyPage(
                    navCreate = { navigationController.navigate(createIdea) },
                    viewModel = ideaviewmodel,
                    navEdit = { navigationController.navigate(editcategory) },
                navIdeas = {navigationController.navigate(idearoute)},
                navProfile = {navigationController.navigate(inspirationroute)},
                navigationInsp = {navigationController.navigate(inspirationroute)},
                navCatagories = {navigationController.navigate(categorypage)})
            }

            composable(route = createIdea) {
                CreateIdea(navIdeas = { navigationController.navigate(idearoute) })
            }
            composable(route=changepassword){
                Password(
                    viewModel = AuthViewModel(),
                    navigation = { navigationController.navigate(profile)},
                )
            }
            composable(route=createcategory){
                CreateCategory(navCategories ={navigationController.navigate(categorypage)},
                viewModel = categoryviewmodel
                )

            }
            composable(route=editcategory){
                categoryviewmodel.selectCategory(ideaviewmodel.uiState.value.currenttitle?:"",ideaviewmodel.uiState.value.currentCategoryID?:"")
                //categoryviewmodel.uiState.value.currenttitle = ideaviewmodel.uiState.value.currenttitle
                CreateCategory(navCategories ={navigationController.navigate(categorypage)},
                viewModel = categoryviewmodel
                )

            }
            composable(route = opencategory){
                EditCategoryButton(navEdit = {navigationController.navigate(createcategory)},
                    viewModel = categoryviewmodel)
            }
            composable(route = delete){
                deleteCategoryButton(navDelete = {navigationController.navigate(deletecategory)},
                    viewModel =categoryviewmodel )
            }
            composable(route=categorypage){
                categoryviewmodel.uiState.value.currentJourneyID = journeyviewmodel.uiState.value.currentJourneyID
                CategoryPage(navCreate = {navigationController.navigate(createcategory)},
                navCategories = {navigationController.navigate(categorypage)},
                navigationInsp = {navigationController.navigate(inspirationroute)},
                navProfile = {navigationController.navigate(inspirationroute)},
                    navIdeas = {navigationController.navigate(idearoute)},
                    navInvite = {navigationController.navigate(inviteroute)},
                    viewModel = categoryviewmodel)
            }
            composable(route=inviteroute){
                invite(viewmodel = colviewmodel)
            }
    }
}}
