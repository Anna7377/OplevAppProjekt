package com.example.oplevappprojekt.sites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.ViewModel.IdeasViewModel
import com.example.oplevappprojekt.ViewModel.Journeysviewmodel
import com.example.oplevappprojekt.data.img

//s215726 & s213370

@Composable
fun CreateIdea(navIdeas: ()->Unit, navCat: ()->Unit, viewModel: IdeasViewModel, journeysviewmodel: Journeysviewmodel, navBack:() ->Unit) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .background(Color(myColourString.toColorInt()))
                .height(480.dp)
                .width(350.dp),

            ) {
            Text(
                text = "Opret Ide",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            )
            Box(modifier = Modifier
                .size(30.dp)
                .absoluteOffset(x = 320.dp, y = 0.dp)){
                Image(painter = painterResource(id = R.drawable.close), contentDescription = "", modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = { navBack() }))
            }
        }

        MaterialTheme(
            content = {
                Column(
                    //modifier = Modifier.height(200.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {
                    Spacer(modifier = Modifier.height(60.dp))
                    var titletext = ""
                    var desctext = ""
                    var linktext = ""
                    var imgText = ""
                    if(viewModel.uiState.value.isIdeaSelected){
                        titletext = viewModel.uiState.value.ideatitle
                        desctext = viewModel.uiState.value.ideadesc
                        linktext = viewModel.uiState.value.idealink
                        imgText = viewModel.uiState.value.ideaimg
                    }


                    val title = nameCat(text =titletext)
                    Spacer(modifier = Modifier
                        .height(10.dp))
                    var text = "Vælg Kategori"
                    if(viewModel.uiState.value.isCategorySelected){
                        text = viewModel.uiState.value.categoryName
                    }

                    viewModel.getCatID(DropDownMenu(viewmodel = journeysviewmodel, text), journeyID)
                    val desc = nameCat(text = desctext)

                    Spacer(modifier = Modifier
                        .height(15.dp))

                    val link = nameCat(text = linktext)

                    Spacer(modifier = Modifier
                        .height(10.dp))

                    val img = nameCat(text = imgText)

                    var nav = navIdeas
if(viewModel.uiState.value.isCategorySelected){
    nav = navBack
}
                    var onClick= { viewModel.createIdea(title = title, desc = desc, link = link, journeyID = journeyID,
                        img = img)
                    nav()}
                    if(viewModel.uiState.value.isIdeaSelected){
                        onClick = {viewModel.editIdea(title = title, desc = desc, link = link,
                            img = img)
                        nav()}

                    }
                    Button(onClick = onClick
                     ,
                        shape = RoundedCornerShape(60.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    ) {
                        Text(
                            text = "Opret",
                            color = Color.Black
                        )
                    }
                }

            })

    }}

@Composable
fun Title() : String {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Transparent),
        modifier = Modifier
            .height(49.dp)
            .width(250.dp)
            .offset(x = 2.dp),
        shape = RoundedCornerShape(8.dp),
        onValueChange = {newText -> {

        }
             },
        label ={
            Text(text = "Titel:",
                color = Color.Gray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold)
        },

        )
return text
}

@Composable
fun Descriptions() : String {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Transparent),
        modifier = Modifier
            .height(120.dp)
            .width(250.dp)
            .offset(x = 2.dp),
        shape = RoundedCornerShape(8.dp),
        onValueChange = {newText ->
            },
        label ={
            Text(text = "Beskrivelse:",
                color = Color.Gray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold)
        },

        )
return text
}

@Composable
fun Link() : String {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Transparent),
        modifier = Modifier
            .height(49.dp)
            .width(250.dp)
            .offset(x = 2.dp),
        shape = RoundedCornerShape(8.dp),
        onValueChange = {newText -> {

        }
        },
        label ={
            Text(text = "Link:",
                color = Color.Gray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold)
        },

        )
    return text
}




@Composable
fun DropDownMenu(viewmodel: Journeysviewmodel, text: String) : String {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedItem by remember {
        mutableStateOf(text)
    }
    val list = viewmodel.getCategories()
    System.out.println("list: " + list)
    val names = arrayListOf<String>()
    for (i in 0..list.size-1){
        names.add(list.get(i).name)
    }

    MaterialTheme(
        content ={
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.height(90.dp)

            ){
                Box(
                    modifier = Modifier
                        .offset(x = 3.dp)
                        .offset(y = 3.dp)
                        .background(Color.White)
                        .height(40.dp)
                        .width(250.dp)
                )
                {
                    TextButton(onClick = {expanded = true}) {
                        Row {
                            Text(text = "$selectedItem",
                                fontSize = 15.sp,
                                color = Color.Black,
                                fontStyle = FontStyle.Italic
                            )
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                        }
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        names.forEach{
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


