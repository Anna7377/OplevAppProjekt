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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.model.Journey
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.data.JourneyData
import com.example.oplevappprojekt.data.JourneysRepository
import com.example.oplevappprojekt.model.Idea
import java.util.*

typealias ComposableFun = @Composable () -> Unit

@Preview
@Composable
fun Previeww() {
MyJourneyPage({})
}


@Composable
fun MyJourneyPage(navCreate: ()-> Unit){
    Scaffold(content = {Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            TopCard(ImageId = R.drawable.image8, text = "Denmark")

            val idea = Idea("titel", "desc")
            val idea2 = Idea("titel", "desc")
            val idea3 = Idea("titel", "desc")
            val idea4 = Idea("titel", "desc")
            val idea5 = Idea("titel", "desc")

            val myideas = arrayListOf(idea, idea2, idea3, idea4, idea5)

            val journey = Journey("Denmark", Date(1), R.drawable.image10, myideas)
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
                index, function ->  item { IdeaBox(null) }
        }


    }
}


@Composable
fun IdeaBox(idea: Idea?) {
    val dialog = remember{ mutableStateOf(false) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.clickable(onClick = {dialog.value=true})
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
            title = { Text(text="Skagen", color = Color.White) },
            text={ Text(text="Husk at tage madpakker med", color = Color.White) },
            confirmButton = { TextButton(onClick = {dialog.value=false}) { Text(text="Luk", color = Color.White) } },
            backgroundColor = Color(myColourString.toColorInt())
        )
    }
}
