package com.example.oplevappprojekt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.oplevappprojekt.ViewModel.Journeysviewmodel
import com.example.oplevappprojekt.data.HardcodedJourneysRepository
import com.example.oplevappprojekt.data.Journey
import com.example.oplevappprojekt.data.JourneyRepository
import com.example.oplevappprojekt.data.JourneysRepository
import com.example.oplevappprojekt.sites.Countries
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Date


//s215726
val myColor = "#455467"



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
            var selectedItem by remember {
                mutableStateOf("Vælg land")
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
                Button(
                    onClick = {
                        //Der skal sørges for, at der på nedenstående newJourney() metode tager værdier
                        //fra dropdown og ikke de hardkodede værdier.
                        viewModel.addJourney(country = selectedItem, date = dato + "/" + month + "/"+year)
                        navMain()
                    },
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
        mutableStateOf("10")
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
        mutableStateOf("10")
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
        mutableStateOf("2020")
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






