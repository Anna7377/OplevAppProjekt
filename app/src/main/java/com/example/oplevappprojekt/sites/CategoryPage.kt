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
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.ViewModel.JourneyViewModel
import com.example.oplevappprojekt.ViewModel.Journeysviewmodel
import com.example.oplevappprojekt.ViewModel.journeyState
import com.example.oplevappprojekt.model.Idea
import com.example.oplevappprojekt.model.Journey
import com.example.oplevappprojekt.ui.theme.Teal200

// s213370

@Preview
@Composable
fun Preview(){
    CategoryPage(navCategories = { /*TODO*/ }, viewModel = Journeysviewmodel(), state = journeyState())
}

@Composable
fun CategoryPage(navCategories: ()-> Unit, viewModel: Journeysviewmodel, state: journeyState){
    Scaffold(content = {
        Surface {
        Column(modifier = Modifier.fillMaxSize()) {


            val idea = Idea("titel", "desc")
            val idea2 = Idea("statue", "husk 50 kr til billeder")
            val idea3 = Idea("Restaurant x", "drik milkshake her")

            val myideas = arrayListOf(idea, idea2, idea3)
            val journey = Journey("Denmark", "2", R.drawable.image10, myideas)
            TopCard(ImageId = R.drawable.image10, text = state.currentcountry.toString())
            Text(text = state.currentdate.toString())

            Button(onClick = { /*TODO*/ },
                modifier = Modifier.padding(20.dp).width(380.dp).height(70.dp).clip(
                    RoundedCornerShape(15)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(myColourString.toColorInt()),
                    contentColor = Color.White)) {
                Text(text = "Kategori", fontSize = 20.sp)
                
            }

            IdeaGrid(journey = journey)}
    }
    },
        floatingActionButton = {Fob(navCreate = navCategories)})
}


