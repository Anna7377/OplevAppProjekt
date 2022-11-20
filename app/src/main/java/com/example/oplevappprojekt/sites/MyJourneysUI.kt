package com.example.oplevappprojekt.sites

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.ViewModel.JourneyViewModel
import com.example.oplevappprojekt.ViewModel.MyJourneysViewModel
import com.example.oplevappprojekt.model.Journey
import com.example.oplevappprojekt.ui.theme.OplevAppProjektTheme
import com.example.scrollablelistofbuttons.data.Datasource
import com.example.scrollablelistofbuttons.model.ScrollableList
import java.util.*


//S213370 & S215722
class MyJourneysUI{

}

// S215722
@Composable
fun  MainPage(navigationInsp: ()-> Unit, viewModel: MyJourneysViewModel, navCreate: ()->Unit){
   Surface(modifier = Modifier.fillMaxSize()) {
       Column(modifier = Modifier
           .fillMaxWidth()) {
           TopCard(ImageId = R.drawable.image10, text = "Mine Rejser")
           FloatingActionButton(onClick = navCreate,
               backgroundColor = Color(myColourString.toColorInt()),
               contentColor = Color.White) {
           }

           if (viewModel.journeyData.journeys.isEmpty()){
               Text(text = "No journeys")
           } else {
               CountryList(list = viewModel.journeyData.journeys)
           }
       }
    }
}

@Composable
fun NewJourneyButton(onClick: ()->Unit){
    FloatingActionButton(onClick = onClick,
        backgroundColor = Color(myColourString.toColorInt()),
        contentColor = Color.White) {
    }
}
@Preview
@Composable
fun MainPrev(){
    val myJourneysViewModel = MyJourneysViewModel()
    MainPage({}, myJourneysViewModel, {})
}

@Composable
fun CountryList(list: List<Journey>){
    LazyColumn {
        items(list) { list.forEach{ 
            CountryCards(img=it.img, country = it.country)
        }

        }
    }
}
@Composable
fun CountryCards(img: Int, country: String) {
    Card(modifier = Modifier.padding(4.dp), elevation = 4.dp) {


        Box() {
            Image(
                painter = painterResource(id =
                img),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )

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

            )

        }
    }
}




//S213370

@Composable
fun ScrollableList() {
    OplevAppProjektTheme {
        ScrollableList(scrollableList = Datasource().loadScrollableList())
    }
}

@Composable
fun CountryCard(scrollablelistofbuttons: ScrollableList, modifier: Modifier = Modifier) {
    Card(modifier = Modifier.padding(4.dp), elevation = 4.dp) {


        Box() {
            Image(
                painter = painterResource(id = scrollablelistofbuttons.imageResourceId),
                contentDescription = stringResource(
                    id = scrollablelistofbuttons.stringResourceId
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = stringResource(id = scrollablelistofbuttons.stringResourceId),
                modifier = Modifier.padding(16.dp),
                //      .border(width = 2.dp, shape = , color = Color.Black),
                style = MaterialTheme.typography.h3,
                //  fontSize = 24.sp
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Cursive,
                //  fontFamily = FontFamily.Serif
                color = Color.White,
                //  textDecoration = TextDecoration.Underline

            )

        }
    }
}


@Composable
private fun ScrollableList(scrollableList: List<ScrollableList>, modifier: Modifier = Modifier){
    LazyColumn {
        items(scrollableList){ scrollableList ->
            CountryCard(scrollablelistofbuttons = scrollableList)
        }
    }
}


@Preview
@Composable
fun CountryCardPreview(){
    OplevAppProjektTheme {
        CountryCard(scrollablelistofbuttons = ScrollableList(R.string.country1, R.drawable.image1))
    }
}
