package com.example.oplevappprojekt.sites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.model.Journey
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.ViewModel.Journeysviewmodel
import com.example.oplevappprojekt.ViewModel.journeyState
import com.example.oplevappprojekt.model.Idea
import java.util.*

typealias ComposableFun = @Composable () -> Unit

//s215722

@Preview
@Composable
fun Previeww() {
MyJourneyPage({}, Journeysviewmodel(), journeyState())
}


@Composable
fun MyJourneyPage(navCreate: ()-> Unit, viewModel: Journeysviewmodel, state: journeyState){
    Scaffold(content = {Surface {
        Column(modifier = Modifier.fillMaxSize()) {


            val idea = Idea("titel", "desc")
            val idea2 = Idea("statue", "husk 50 kr til billeder")
            val idea3 = Idea("Restaurant x", "drik milkshake her")

            val myideas = arrayListOf(idea, idea2, idea3)
            val journey = Journey("Denmark", "2", R.drawable.image10, myideas)
            TopCard(ImageId = R.drawable.image10, text = state.currentcountry.toString())
            Text(text = state.currentdate.toString())
            IdeaGrid(journey = journey)}
        }
    },
        floatingActionButton = {Fob(navCreate = navCreate)})
}


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
fun IdeaBox(idea: Idea?) {
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
