package com.example.oplevappprojekt.sites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.ViewModel.CollaboratorViewmodel
import com.example.oplevappprojekt.ViewModel.Journeysviewmodel
import com.example.oplevappprojekt.data.Countries


//S215722

// the main screen shown
@Composable
fun  MainPage(navigationInsp: ()-> Unit,
              navCreate: ()->Unit, navProfile: ()->Unit, navIdeas: () -> Unit,
viewModel: Journeysviewmodel, navInvite: ()->Unit, navCategories: ()->Unit){
  Scaffold(bottomBar = {BottomBar(onClick1 = {navigationInsp()}, onClick2 = { /*TODO*/ }, onClick3 = {navProfile()})},
      content =
      {
          Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
              Column(
                  modifier = Modifier
                      .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
              ) {
                  var journeylist: ArrayList<com.example.oplevappprojekt.ViewModel.Journey>
                  TopCard(ImageId = R.drawable.map, text = "Mine Rejser")
                  ChangePageText(text = "join via link", onClick = {navInvite()})
                 viewModel.getJourneys()
                      journeylist = viewModel.uiState.value.userjourneys
                  if (journeylist.isEmpty()) {
                      Text(text = "Ingen Rejser", color = Color.White, textAlign = TextAlign.Center, fontSize = 40.sp)
                  } else {
                      CountryList(list = journeylist, navIdeas = navIdeas, viewmodel = viewModel)
                  } } } },
  floatingActionButton = {Fob({navCreate()
      viewModel.deselect()})})
}

// list of cards for journeys
@Composable
fun CountryList(viewmodel: Journeysviewmodel, list: ArrayList<com.example.oplevappprojekt.ViewModel.Journey>, navIdeas: ()-> Unit){
    LazyColumn {
        items(list) {
            CountryCards(img=R.drawable.image11,
                country = it.country,
                navIdeas=navIdeas,
                viewModel = viewmodel,
                date=it.date,
            ID=it.JourneyID)
        } } }

@Composable
fun CountryCards(img: Int, country: String, date: String, ID: String, navIdeas: ()-> Unit, viewModel: Journeysviewmodel) {

    Card(modifier = Modifier
        .padding(4.dp)
        .clickable(onClick = {
            viewModel.selectJourney(country = country, date = date, ID = ID)
            navIdeas()
        })
        , elevation = 4.dp) {

        Box() {
            Image(
                painter = painterResource(id =
                img),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop )
            Text(
                text = country,
                modifier = Modifier.padding(16.dp),
                //      .border(width = 2.dp, shape = , color = Color.Black),
                style = MaterialTheme.typography.h3,
                //  fontSize = 24.sp
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Cursive,
                //  fontFamily = FontFamily.Serif
                color = Color.White,
                //  textDecoration = TextDecoration.Underline
            ) } } }

//s215726
val myColor = "#455467"




// Acts as a popup
@Composable
fun Trip(navMain: ()->Unit, viewModel: Journeysviewmodel) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .background(Color(myColor.toColorInt()))
                .height(350.dp)
                .width(350.dp),
        )

        {
            Text(
                text = "Nyt Rejsemål",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp)
            )
            var expanded by remember {
                mutableStateOf(false)
            }
            var temp = "Vælg land"
            if(viewModel.uiState.value.isJourneySelected){
                temp = viewModel.uiState.value.currentcountry.toString()
            }
            var selectedItem by remember {
                mutableStateOf(temp)
            }
            var list = Countries.countries

            MaterialTheme(
                content ={
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.height(200.dp)

                    ){
                        Box(
                            modifier = Modifier
                                .offset(x = 30.dp)
                                .offset(y = 30.dp)
                                .background(Color.White)
                                .height(30.dp)
                                .width(250.dp)
                        )
                        {
                            TextButton(onClick = {expanded = true}) {
                                Row {
                                    Text(text = "$selectedItem",
                                        fontSize = 10.sp,
                                        color = Color.Black,
                                    )
                                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                                }
                            }
                            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                                list.forEach{
                                    DropdownMenuItem(onClick = {
                                        expanded = false
                                        selectedItem = it
                                    }) {
                                        Text(text = it)
                                    }
                                }
                            }
                        }
                    }


                }
            )
            Text(
                text = "Rejsedato",
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(30.dp)
                    .offset(y = 160.dp)
            )
            val dato = Dato()
            val month = Month()
            val year = Year()



            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 275.dp)
            ){
                var onClick = {viewModel.addJourney(country = selectedItem, date = dato + "/" + month + "/"+year)
                    navMain() }
                System.out.println("is Journey selected? " + viewModel.uiState.value.isJourneySelected)
                if(viewModel.uiState.value.isJourneySelected){
                    onClick = {viewModel.editJourney(country = selectedItem,
                        date =dato + "/" + month + "/"+year,
                        ID=viewModel.uiState.value.currentJourneyID.toString())
                        navMain()}
                }
                Button(
                    onClick = onClick,
                    shape = RoundedCornerShape(60.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                ) {
                    Text(
                        text = "Opret",
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun Dato(): String {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedItem by remember {
        mutableStateOf("Dag")
    }

    var list = listOf("1", "2", "3", "4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31")

    MaterialTheme(
        content ={
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.height(400.dp)

            ){
                Box(
                    modifier = Modifier
                        .offset(x = 30.dp)
                        .offset(y = 55.dp)
                        .background(Color.White)
                        .height(30.dp)
                        .width(80.dp)
                )
                {
                    TextButton(onClick = {expanded = true}) {
                        Row {
                            Text(text = "$selectedItem",
                                fontSize = 10.sp,
                                color = Color.Black
                            )
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                        }
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        list.forEach{
                            DropdownMenuItem(onClick = {
                                expanded = false
                                selectedItem = it
                            }) {
                                Text(text = it)
                            }
                        }
                    }
                }
            }
        }
    )
    return selectedItem
}



@Composable
fun Month() : String {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedItem by remember {
        mutableStateOf("Måned")
    }
    var list = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")

    MaterialTheme(
        content ={
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.height(400.dp)

            ){
                Box(
                    modifier = Modifier
                        .offset(x = 112.dp)
                        .offset(y = 55.dp)
                        .background(Color.White)
                        .height(30.dp)
                        .width(80.dp)
                )
                {
                    TextButton(onClick = {expanded = true}) {
                        Row {
                            Text(text = "$selectedItem",
                                fontSize = 10.sp,
                                color = Color.Black
                            )
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                        }
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        list.forEach{
                            DropdownMenuItem(onClick = {
                                expanded = false
                                selectedItem = it
                            }) {
                                Text(text = it)
                            }
                        }
                    }
                }
            }
        }
    )
    return selectedItem
}
@Composable
fun Year() : String {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedItem by remember {
        mutableStateOf("År")
    }
    var list = listOf("2020", "2021", "2022", "2023","2024","2025","2026","2027","2028","2029","2030")

    MaterialTheme(
        content ={
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.height(400.dp)

            ){
                Box(
                    modifier = Modifier
                        .offset(x = 194.dp)
                        .offset(y = 55.dp)
                        .background(Color.White)
                        .height(30.dp)
                        .width(80.dp)
                )
                {
                    TextButton(onClick = {expanded = true}) {
                        Row {
                            Text(text = "$selectedItem",
                                fontSize = 10.sp,
                                color = Color.Black
                            )
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                        } }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        list.forEach{
                            DropdownMenuItem(onClick = {
                                expanded = false
                                selectedItem = it
                            }) {
                                Text(text = it)
                            } } } } } })
    return selectedItem
}













//S213370

