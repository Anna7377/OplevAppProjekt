package com.example.oplevappprojekt.sites

import android.app.AlertDialog
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.ViewModel.Ideaviewmodel
import com.example.oplevappprojekt.ViewModel.Journeysviewmodel
import com.example.oplevappprojekt.model.Idea
import com.example.oplevappprojekt.model.Journey
import java.util.*
import kotlin.collections.ArrayList


//s215722 & s213370 & s215718

//skal den laves til Ideaviewmodel fra journey? også i mainA i myjourneypage?

@Composable
fun MyJourneyPage(navigationInsp: () -> Unit,
                  navCreate: ()-> Unit,
                  navIdeas: () -> Unit,
                  navProfile: () -> Unit,
                  viewModel: Ideaviewmodel,
                  navEdit: ()->Unit
){

    Scaffold( bottomBar = { BottomBar(onClick1 = {navigationInsp}, onClick2 = { /*TODO*/ }, onClick3 = {navProfile})},

        content =
        {
            Surface (modifier = Modifier.fillMaxSize(), color = Color.Black) {

                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {


                    var idealist: ArrayList<com.example.oplevappprojekt.ViewModel.Idea> = viewModel.uiState.value.userideas
                    TopCard(ImageId = R.drawable.map, text = "Mine Ideer")
                    viewModel.getIdeas()
                    idealist = viewModel.uiState.value.userideas
                    if (idealist.isEmpty()) {
                        Text(text = "Ingen Ideer", color = Color.White, textAlign = TextAlign.Center, fontSize = 40.sp)
                    } else
                        IdeaList(list = idealist, navIdeas = navIdeas, viewModel = viewModel)
                }

            }
        },
        floatingActionButton = {Fob(navCreate = navCreate)})
}


@Composable
fun IdeaList(viewModel: Ideaviewmodel, list: ArrayList<com.example.oplevappprojekt.ViewModel.Idea>, navIdeas: ()-> Unit){
    LazyColumn() {
        items(list) {
            IdeaCards(
                desc = it.description,
                title = it.title,
                img=R.drawable.image11,
                ID = it.JourneyID,
                il = it.categoryID,
                navIdeas=navIdeas,
                viewModel = viewModel,
            )

        } } }

@Composable
fun IdeaCards(img:Int, title:String, desc: String, ID: String, il: String, navIdeas: () -> Unit,viewModel: Ideaviewmodel){
    Card(modifier = Modifier
        .padding(20.dp)
        .clickable {
            viewModel.selectIdea(title = title, description = desc, ID = ID, il = il)
            navIdeas()
        }) {
        Box() {
            Image(
                painter = painterResource(id = img),
                contentDescription ="",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = title,
                modifier = Modifier
                    .padding(20.dp)
                    .width(380.dp)
                    .height(70.dp)
                    .clip(RoundedCornerShape(15))
            )

            Text(
                text = desc,
                modifier = Modifier
                    .padding(20.dp)
                    .width(380.dp)
                    .height(70.dp)
                    .clip(RoundedCornerShape(15)))

        }

    }
}










/*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IdeaGrid(journey : Journey){
    val itemsinColumn = mutableListOf<ComposableFun>()

   for (idea in journey.IdeaList){

       val tempIdea: ComposableFun = {
           IdeaBox(idea = idea)
       }

       itemsinColumn.add(tempIdea)
   }


    LazyVerticalGrid(cells = GridCells.Fixed(2)){

        itemsinColumn.forEachIndexed{
                index, function ->  item { IdeaBox(journey.IdeaList.get(index)) }
        }


    }
}


@Composable
fun IdeaBox(idea: Idea?, viewmodel: Ideaviewmodel, desc: String, title: String, img: Int, ID: String, navIdeas: () -> Unit) {
    val dialog = remember{ mutableStateOf(false) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier
            .clickable(onClick = { dialog.value = true })
            .width(200.dp)
            .height(200.dp)
            .padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
            .clip(RoundedCornerShape(15))) {
            Image(
                painter = painterResource(id = R.drawable.image11),
                contentDescription = " "
            )
        }
        if (idea != null) {
            Text(text=idea.title, textAlign = TextAlign.Center)
        }
        else {
            Text(text= "Skagen", textAlign = TextAlign.Center)
        }
        }

    if(dialog.value){
        AlertDialog(onDismissRequest = {dialog.value=false},
            title = {
                if (idea != null) {
                    Text(text=idea.title, color = Color.White)
                }
                else {
                    Text(text="titel", color = Color.White)
                }
            },
            text={
                if (idea != null) {
                    Text(text=idea.desc, color = Color.White)
                }
                else {
                    Text(text="description", color = Color.White)
                }
            },
            confirmButton = { TextButton(onClick = {dialog.value=false}) { Text(text="Luk", color = Color.White) } },
            backgroundColor = Color(myColourString.toColorInt())
        )
    }
}

@Composable
fun editJourney(navEdit: () -> Unit){
    Button(onClick = {navEdit()}, colors = ButtonDefaults.buttonColors(Color(myColourString.toColorInt()))) {
        Text(text="Rediger Rejse", color = Color.White)
    }
}
@Composable
fun deleteJourney(navMain: ()-> Unit, viewModel: Journeysviewmodel) {
    Button(onClick = {
        navMain()
        viewModel.deleteJourney()
    }, colors = ButtonDefaults.buttonColors(Color.Red)) {
        Text(text="Slet Rejse", color = Color.White)
    } }

@Composable
fun genLink(viewModel: Journeysviewmodel){
    val dialog = remember{mutableStateOf(false)}

    if(dialog.value){
        AlertDialog(onDismissRequest = {dialog.value=false},
            title = { Text(text="Inviter Medarrangør", color = Color.White) },
            text={ SelectionContainer() {
                Text(text= viewModel.uiState.value.currentJourneyID.toString(),
                color = Color.White, ) }},
            confirmButton = { TextButton(onClick = {dialog.value=false}) { Text(text="luk", color = Color.White) } },
            backgroundColor = Color(myColourString.toColorInt()))
    }
    Button(onClick = {dialog.value=true}) {
Text("Inviter Medarrangør")
    }
}




/*

            Row{
                editJourney(navEdit = {navEdit()})
                deleteJourney(navMain = {navMain()}, viewModel = viewModel)
                genLink(viewModel = viewModel)
            }

            IdeaGrid(journey = journey)


        */



 */


