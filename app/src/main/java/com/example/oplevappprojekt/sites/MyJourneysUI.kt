package com.example.oplevappprojekt.sites

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.ViewModel.Journeysviewmodel
import com.example.oplevappprojekt.ViewModel.journeyState
import com.example.oplevappprojekt.data.HardcodedJourneysRepository
import com.example.oplevappprojekt.ui.theme.OplevAppProjektTheme
import com.example.scrollablelistofbuttons.model.ScrollableList
import kotlinx.coroutines.runBlocking


//S213370 & S215722
class MyJourneysUI{
}
// S215722
@Composable
fun  MainPage(navController: NavController, navigationInsp: ()-> Unit,
              navCreate: ()->Unit, navProfile: ()->Unit, navIdeas: () -> Unit,
viewModel: Journeysviewmodel){

  Scaffold(bottomBar = {BottomBar(onClick1 = {navigationInsp()}, onClick2 = { /*TODO*/ }, onClick3 = {navProfile()})},
      content =
      {
          Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
              Column(
                  modifier = Modifier
                      .fillMaxWidth()
              ) {
                  var journeylist: ArrayList<com.example.oplevappprojekt.ViewModel.Journey>
                  TopCard(ImageId = R.drawable.map, text = "Mine Rejser")
                 viewModel.getJourneys()
                      journeylist = viewModel.uiState.value.userjourneys
                  if (journeylist.isEmpty()) {
                      Text(text = "No journeys")
                  } else {
                      CountryList(list = journeylist, navIdeas = navIdeas, viewmodel = viewModel)
                  } } } },
  floatingActionButton = {Fob({navCreate()})})
}


@Composable
fun CountryList(viewmodel: Journeysviewmodel, list: ArrayList<com.example.oplevappprojekt.ViewModel.Journey>, navIdeas: ()-> Unit){
    LazyColumn {
        items(list) {
            CountryCards(img=R.drawable.image11,
                country = it.country,
                navIdeas=navIdeas,
                viewModel = viewmodel,
                date=it.date)
        } } }

@Composable
fun CountryCards(img: Int, country: String, date: String, navIdeas: ()-> Unit, viewModel: Journeysviewmodel) {

    Card(modifier = Modifier.padding(4.dp).clickable(onClick = {viewModel.selectJourney(country=country, date=date)
        navIdeas()})
        , elevation = 4.dp) {

        Box() {
            Image(
                painter = painterResource(id =
                img),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop )
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
            ) } } }




//S213370

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
