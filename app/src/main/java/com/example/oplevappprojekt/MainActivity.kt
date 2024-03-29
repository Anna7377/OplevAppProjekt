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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import invite


class MainActivity : ComponentActivity() {

    var start = "start"
    public override fun onStart() {
        super.onStart()
        val currentuser = Firebase.auth.currentUser
        if(currentuser!=null){
start = "main"
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            OplevApp(start = start)
        }
    }
}


@Composable
fun OplevApp(start: String) {
    OplevAppProjektTheme {
        val journeyviewmodel = Journeysviewmodel()
        val colviewmodel = CollaboratorViewmodel()
        val authviewmodel = AuthViewModel()
        val ideasViewModel = IdeasViewModel()
        val startRoute = "start"
        val loadRoute = "load"
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
            startDestination = start
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
                invite(viewmodel = colviewmodel,
                navBack = { navigationController.navigate(mainroute) } )
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
                    navProfile = { navigationController.navigate(profile) }, InspirationViewModel())
            }
            composable(route = createroute) {
                Trip(
                    navMain = { navigationController.navigate(mainroute) },
                    navBack = {navigationController.navigate(mainroute)},
                    viewModel = journeyviewmodel
                )
            }
            composable(route = profile) {
                UserProfile(navigationInspo = { navigationController.navigate(inspirationroute) },
                    navMain = { navigationController.navigate(mainroute) },
                    viewModel = authviewmodel,
                    navStart = { navigationController.navigate(startRoute) },
                    navChange = { navigationController.navigate(changepassword) }
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
                    viewModelIdea = ideasViewModel,
                    viewModelcol = colviewmodel,
                    navEdit = { navigationController.navigate(createroute) },
                    navMain = {navigationController.navigate(mainroute)},
                    navCatIdeas = {navigationController.navigate(catideas)}, createCat = { navigationController.navigate(createcatbackup) },
                    navProfile ={ navigationController.navigate(profile) },
                navLoad = {navigationController.navigate("load")},
                navCreateIdea = {navigationController.navigate(createIdea)})
            }
            composable(route = createIdea) {
                CreateIdea(navIdeas = { navigationController.navigate(catideas) },
                    navCat = {navigationController.navigate(catideas)},
                    viewModel = ideasViewModel, journeyviewmodel,
                navBack = {navigationController.navigate(idearoute)})
            }
            composable(route = changepassword) {
                Password(
                    viewModel = AuthViewModel(),
                    navigation = { navigationController.navigate(profile) },
                )
            }

            composable(route = createcatbackup){
                createcat(
                    navDash = {navigationController.navigate(idearoute)},
                    viewModel = journeyviewmodel,
                    ideasViewModel = ideasViewModel,
                    navBack = {navigationController.navigate(idearoute)}
                )
            }
            composable(route = intermediarycreate){
                createOpt(navCat = {navigationController.navigate(createcatbackup)},
                navIdea={ navigationController.navigate(createIdea) } , ideasViewModel, navBack = {navigationController.navigate(idearoute)})}
            composable(route = catideas){
              IdeasPg(viewModel = ideasViewModel, navCreate = {navigationController.navigate(createIdea)},
              journeyviewmodel, navLoad = {navigationController.navigate(loadRoute)},
              navDash = {navigationController.navigate(idearoute)})
            }
        }
    }
}
