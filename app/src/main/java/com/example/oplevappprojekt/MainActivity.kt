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
import com.example.oplevappprojekt.ViewModel.AuthViewModel
import com.example.oplevappprojekt.ViewModel.Journeysviewmodel
import com.example.oplevappprojekt.ViewModel.journeyState
import com.example.oplevappprojekt.data.backupRepoCat
import com.example.oplevappprojekt.sites.*
import com.example.oplevappprojekt.ui.theme.OplevAppProjektTheme
import invite


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
fun OplevApp() {
    OplevAppProjektTheme {
        val journeyviewmodel = Journeysviewmodel()
        val colviewmodel = CollaboratorViewmodel()
        val authviewmodel = AuthViewModel()
        val ideasViewModel = IdeasViewModel()
        val startRoute = "start"
        val mainroute = "main"
        val profile = "profile"
        val loginRoute = "logIn"
        val signupRoute = "signUp"
        val createIdea = "Create idea"
        val idearoute = "idea"
        val inspirationroute = "inspiration"
        val createroute = "create"
        val createcategory = "create category"
        val categorypage = "categeory page"
        val changepassword = "password"
        val inviteroute = "invite"
        val createcatbackup = "ccb"
        val catideas = "catideas"
     val    intermediarycreate = "inter"
        val navigationController = rememberNavController()
        /* must be changed such that the startroute is defined by whether the user is logged in or not */
        NavHost(
            navController = navigationController,
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
                    navigation = { navigationController.navigate(loginRoute) }
                ) { navigationController.navigate(mainroute) }
            }
            composable(route = inviteroute) {
                invite(viewmodel = colviewmodel)
            }
            composable(route = mainroute) {
                MainPage(navigationInsp = { navigationController.navigate(inspirationroute) },
                    navCreate = { navigationController.navigate(createroute) },
                    navProfile = { navigationController.navigate(profile) },
                    navIdeas = { navigationController.navigate(idearoute) },
                    viewModel = journeyviewmodel,
                    navInvite = {
                        navigationController.navigate(inviteroute)
                    },
                    navCategories = { navigationController.navigate(categorypage) })
            }
            composable(route = inspirationroute) {
                Inspiration(
                    navMain = { navigationController.navigate(mainroute) },
                    navProfile = { navigationController.navigate(profile) })
            }
            composable(route = createroute) {
                Trip(
                    navMain = { navigationController.navigate(mainroute) },
                    viewModel = journeyviewmodel
                )
            }
            composable(route = profile) {
                UserProfile(navigationInspo = { navigationController.navigate(inspirationroute) },
                    navMain = { navigationController.navigate(mainroute) },
                    viewModel = authviewmodel,
                    navStart = { navigationController.navigate(startRoute) },
                    // navChange = { navigationController.navigate(changepassword) }
                )
            }
            composable(
                route = idearoute
                //,arguments = listOf(navArgument("country"){type= NavType.StringType},
                // navArgument("date"){type= NavType.StringType}
            ) {
                MyJourneyPage(
                    navCreate = { navigationController.navigate(intermediarycreate) },
                    viewModel = journeyviewmodel,
                    navEdit = { navigationController.navigate(createroute) },
                    navMain = {navigationController.navigate(mainroute)},
                    navCreateIdea = {navigationController.navigate(createcategory)}
               , viewModelIdea = ideasViewModel,  navCatIdeas = {navigationController.navigate(catideas)},
                createCat = {navigationController.navigate(createcatbackup)})
            }
            composable(route = createIdea) {
                CreateIdea(navIdeas = { navigationController.navigate(idearoute) }, ideasViewModel)
            }
            composable(route = changepassword) {
                Password(
                    viewModel = AuthViewModel(),
                    navigation = { navigationController.navigate(profile) },
                )
            }
            composable(route = createcategory) {
                CreateCategory(navCategories = { navigationController.navigate(categorypage) })

            }
            composable(route = categorypage) {
                CategoryPage(
                    navCategories =
                    { navigationController.navigate(createcategory) },
                    Journeysviewmodel(), journeyState()
                )
            }
            composable(route = createcatbackup){
                createcat(navDash = {navigationController.navigate(idearoute)},
                    repo = backupRepoCat(), viewModel = journeyviewmodel)
            }
            composable(route = intermediarycreate){
                createOpt(navCat = {navigationController.navigate(createcatbackup)},
                navIdea={ navigationController.navigate(createIdea) } )}
            composable(route = catideas){
            ideas(viewModel = ideasViewModel) {

                }
            }
        }
    }
}
