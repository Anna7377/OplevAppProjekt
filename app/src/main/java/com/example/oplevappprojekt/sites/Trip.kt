package com.example.oplevappprojekt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.oplevappprojekt.model.Screen


val myColor = "#455467"

@Composable
fun CreateTrip(
    navController: NavController
) {
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
            var list = listOf("Danmark", "USA", "Norge", "England")
            //var list = arrayOf(countries)

            MaterialTheme(
                content = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.height(200.dp)

                    ) {
                        Box(
                            modifier = Modifier
                                .offset(x = 30.dp)
                                .offset(y = 30.dp)
                                .background(Color.White)
                                .height(30.dp)
                                .width(250.dp)
                        )
                        {
                            TextButton(onClick = { expanded = true }) {
                                Row {
                                    Text(
                                        text = "$selectedItem",
                                        fontSize = 10.sp,
                                        color = Color.Black,
                                    )
                                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                                }
                            }
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }) {
                                list.forEach {
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
            Date()
            Month()
            Year()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
                    .offset(y = 275.dp)
            ){
                Button(onClick = {
                    navController.navigate(route = Screen.Trips.route)
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
        }}}

@Composable
fun Date() {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedItem by remember {
        mutableStateOf("Dato")
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
}

@Composable
fun Month(){
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedItem by remember {
        mutableStateOf("Måned")
    }
    var list = listOf("Januar", "Februar", "Marts", "April","Maj","Juni","Juli","August","September","Oktober","November","December")

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
}
@Composable
fun Year() {
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
}