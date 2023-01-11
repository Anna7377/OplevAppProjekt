package com.example.oplevappprojekt.sites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.oplevappprojekt.ViewModel.Journeysviewmodel
import com.example.oplevappprojekt.data.backupRepoCat
import com.example.oplevappprojekt.data.category
import com.example.oplevappprojekt.myColor

// to create category
@Composable
fun createcat(navDash: ()->Unit, repo: backupRepoCat, viewModel: Journeysviewmodel){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .background(Color(myColourString.toColorInt()))
                .height(350.dp)
                .width(350.dp),

            ) {
            Text(
                text = "Opret Kategori",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp)
            )
        }

        MaterialTheme(
            content = {
                Column(
                    //modifier = Modifier.height(200.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {
                    val name = nameCat()

                    Button(onClick = {
                        repo.setcategory(name = name, ID = viewModel.uiState.value.currentJourneyID.toString())
                        navDash()
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

            })
    }
}

@Composable
fun nameCat() : String{
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
fun dashBoard(){

}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun catCardList(categorylist: ArrayList<String>) {
    val itemsinColumn = mutableListOf<ComposableFun>()

    for (name in categorylist) {

        val tempIdea: ComposableFun = {
            catCard(name = name)
        }

        itemsinColumn.add(tempIdea)
    }


    LazyVerticalGrid(cells = GridCells.Fixed(1)) {

        itemsinColumn.forEachIndexed { index, function ->
            item { catCard(name = categorylist.get(index)) }
        }

    }
}
@Composable
fun catCard(name: String){
    Card(modifier = Modifier
        .padding(4.dp)
        .clickable(onClick = {
        })
        , elevation = 4.dp) {

        Box(modifier = Modifier.background(color = Color(myColourString.toColorInt()))) {
            Text(
                text = name,
                modifier = Modifier.padding(16.dp),
                //      .border(width = 2.dp, shape = , color = Color.Black),
                style = MaterialTheme.typography.h3,
                //  fontSize = 24.sp
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Cursive,
                //  fontFamily = FontFamily.Serif
                color = Color.White,
                //  textDecoration = TextDecoration.Underline
            ) } }
}