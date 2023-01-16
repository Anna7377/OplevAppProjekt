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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.R
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
          Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
              Column(
                  modifier = Modifier
                      .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
              ) {
                  Box(
                      modifier = Modifier
                          .height(200.dp)
                          .fillMaxWidth()
                  ){
                      Image(painter = painterResource(id = R.drawable.topmap1), contentDescription = "topmap",
                          modifier = Modifier.fillMaxSize())}
                  var journeylist: ArrayList<com.example.oplevappprojekt.ViewModel.Journey>

                  //TopCard(ImageId = R.drawable.topmap1, text = "Mine Rejser")
                  ChangePageText(text = "Tilføj rejse via link", onClick = {navInvite()})
                 viewModel.getJourneys()
                      journeylist = viewModel.uiState.value.userjourneys
                  if (journeylist.isEmpty()) {
                      Text(text = "Ingen Rejser", fontStyle = FontStyle.Italic, fontSize = 30.sp, color = Color(myColourString.toColorInt()), textAlign = TextAlign.Center)
                  } else {
                      CountryList(list = journeylist, navIdeas = navIdeas, viewmodel = viewModel)
                  } } } },
  floatingActionButton = {Fob({navCreate()
      viewModel.deselect()
  })})
}

// list of cards for journeys
@Composable
fun CountryList(viewmodel: Journeysviewmodel, list: ArrayList<com.example.oplevappprojekt.ViewModel.Journey>, navIdeas: ()-> Unit){
    LazyColumn {
        items(list) {
            CountryCards(img=it.img,
                country = it.country,
                navIdeas=navIdeas,
                viewModel = viewmodel,
                date=it.date,
            ID=it.JourneyID,
           originalJourneyID = it.originalJourneyID)
        } } }

@Composable
fun CountryCards(originalJourneyID: String, img: Int, country: String, date: String, ID: String, navIdeas: ()-> Unit, viewModel: Journeysviewmodel) {

    Card(modifier = Modifier
        .padding(4.dp)
        .height(150.dp)
        .width(350.dp)
        .clickable(onClick = {
/*
            viewModel.selectJourney(
                country = country,
                date = date,
                ID = ID,
                originalJourneyID = originalJourneyID
            )
            
 */

            viewModel.selectJourney(country = country, date = date, ID = ID, originalJourneyID = originalJourneyID, img = img)

            navIdeas()
        })
        , elevation = 4.dp) {

        Box() {
            Image(
                painter = painterResource(id = img ),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(),
                    //.height(194.dp),
                contentScale = ContentScale.Crop )
            Text(
                text = country,
                modifier = Modifier.padding(16.dp),
                //      .border(width = 2.dp, shape = , color = Color.Black),
                //style = MaterialTheme.typography.h3,
                fontSize = 30.sp,
                style = androidx.compose.ui.text.TextStyle(shadow = Shadow(color = Color.DarkGray, offset = Offset(7f, 5f), blurRadius = 5f)),
                fontWeight = FontWeight.Bold,
                //fontFamily = FontFamily.Cursive,
                //  fontFamily = FontFamily.Serif
                color = Color.White,
                //  textDecoration = TextDecoration.Underline
            ) } } }

//s215726
val myColor = "#455467"




// Acts as a popup
@Composable
fun Trip(navMain: ()->Unit, viewModel: Journeysviewmodel, navBack: ()-> Unit) {
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
                    .offset(y = 235.dp)
            ){
                var onClick = {viewModel.addJourney(country = selectedItem, date = dato + "/" + month + "/"+year)
                    navMain() }
                System.out.println("is Journey selected? " + viewModel.uiState.value.isJourneySelected)
                if(viewModel.uiState.value.isJourneySelected){
                    var pinVal = viewModel.uiState.value.isPinned
                    onClick = {viewModel.editJourney(country = selectedItem,
                        date =dato + "/" + month + "/"+year,
                        ID=viewModel.uiState.value.currentJourneyID.toString(), pinVal)
                        navMain()}
                }

                IconButton(onClick ={
                    val oldPinVal = viewModel.uiState.value.isPinned
                    val newPinVal = !oldPinVal

                    if (viewModel.uiState.value.isJourneySelected) {
                        viewModel.editJourney(
                            country = selectedItem,
                            date = dato + "/" + month + "/" + year,
                            ID = viewModel.uiState.value.currentJourneyID.toString(), newPinVal
                        )
                        navMain()
                    }}) {
                    Image(painter = painterResource(id = R.drawable.pin), contentDescription = "Pin", Modifier.size(80.dp)
                    )

                }


                /*
                TextButton(onClick = {

                    val oldPinVal = viewModel.uiState.value.isPinned
                    val newPinVal = !oldPinVal

                    if (viewModel.uiState.value.isJourneySelected) {
                        viewModel.editJourney(
                            country = selectedItem,
                            date = dato + "/" + month + "/" + year,
                            ID = viewModel.uiState.value.currentJourneyID.toString(), newPinVal
                        )
                        navMain()
                    }
                }


                ) {
                    Text(text = "Pin", color = Color.Red)
                }*/

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
            Box(modifier = Modifier
                .size(30.dp)
                .absoluteOffset(x = 320.dp, y = 0.dp)){
                Image(painter = painterResource(id = R.drawable.close), contentDescription = "", modifier = Modifier.fillMaxSize().clickable(onClick = {navBack()}))}
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

