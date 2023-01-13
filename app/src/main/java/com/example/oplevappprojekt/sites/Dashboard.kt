package com.example.oplevappprojekt.sites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.ViewModel.CollaboratorViewmodel
import com.example.oplevappprojekt.ViewModel.IdeasViewModel
import com.example.oplevappprojekt.ViewModel.Journeysviewmodel
import com.example.oplevappprojekt.ViewModel.ideas
import java.util.*
import kotlin.collections.ArrayList


typealias ComposableFun = @Composable () -> Unit
var countryname: String = ""
var journeyID = " "
//s215722
@Composable
fun MyJourneyPage(
    navCreate: () -> Unit,
    viewModel: Journeysviewmodel,
    viewModelIdea: IdeasViewModel,
    navEdit: () -> Unit,
    navMain: () -> Unit,
    navCreateIdea: ()->Unit,
    navCatIdeas: ()->Unit,
    createCat: ()->Unit
){
    Scaffold(content = {Surface {
        Column(modifier = Modifier.fillMaxSize()) {
countryname = viewModel.uiState.value.currentcountry.toString()
            journeyID=viewModel.uiState.value.currentJourneyID.toString()
            TopCard(ImageId = R.drawable.image10,
                text = viewModel.uiState.value.currentcountry.toString())
            Text(text = viewModel.uiState.value.currentdate.toString())

            Row{
                if(viewModel.uiState.value.isOwned){
                editJourney(navEdit = {navEdit()})
                genLink(viewModel = viewModel)
                deleteJourney(navMain = {navMain()}, viewModel = viewModel)}
                else{
                    uncollab(viewModel = CollaboratorViewmodel(), orig =viewModel.uiState.value.currentJourneyID.toString() ) {

                    }
                }
            }
            val categories = viewModel.getCategories()
            catCardList(catList = categories, viewModel = viewModelIdea, navCatIdeas, navEdit=createCat)
            IdeaGrid(list = viewModel.getOtherIdeas())}
    }
    },
        floatingActionButton = {Fob(navCreate = navCreate)
      viewModelIdea.deselect()
        })
}


 @OptIn(ExperimentalFoundationApi::class)
@Composable
fun IdeaGrid(list: ArrayList<ideas>){
    val itemsinColumn = mutableListOf<ComposableFun>()

   for (idea in list){
       val tempIdea: ComposableFun = {
           IdeaBox(idea = idea)
       }
       itemsinColumn.add(tempIdea)
   }
    LazyVerticalGrid(cells = GridCells.Fixed(2)){

        itemsinColumn.forEachIndexed{
                index, function ->  item { IdeaBox(list.get(index)) }
        }
    }
}




@Composable
fun IdeaBox(idea: ideas) {
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
        Text(text=idea.title, textAlign = TextAlign.Center)
        }

    if(dialog.value){
        AlertDialog(onDismissRequest = {dialog.value=false},
            title = {
                Text(text=idea.title, color = Color.White)
            },
            text={
                Text(text=idea.desc +
                        SelectionContainer(){
                    idea.link
                }  , color = Color.White)
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

/* @Composable
fun showCol(viewModel: CollaboratorViewmodel){
    val dialog = remember{mutableStateOf(false)}

    if(dialog.value){
        AlertDialog(onDismissRequest = {dialog.value=false},
            title = { Text(text="Medarrangørere", color = Color.White) },
            text={ SelectionContainer() {
               Text(text= viewModel.showCol(),
                    color = Color.White, ) }},
            confirmButton = { TextButton(onClick = {dialog.value=false}) { Text(text="luk", color = Color.White) } },
            backgroundColor = Color(myColourString.toColorInt()))
    }
    Button(onClick = {dialog.value=true}) {
        Text("Inviter Medarrangør")
    }
}

 */

@Composable
fun uncollab(viewModel: CollaboratorViewmodel, orig: String, navMain: () -> Unit){
    Button(onClick = {  viewModel.uncollab(orig)
   navMain() }){
        Text("fjern rejse")
    }

}

@Composable
fun createOpt(navCat: ()->Unit, navIdea: ()->Unit){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {    Box(
        modifier = Modifier
            .background(Color(myColor.toColorInt()))
            .height(350.dp)
            .width(350.dp),
    ) {

        Text(
            text = "Opret",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
        )}
        MaterialTheme(
            content =
            {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(onClick = { navCat() },
                            shape = RoundedCornerShape(4), colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
                            Text(text = "Kategori", color = Color(myColourString.toColorInt()))
                        }
                    Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Eller", color = Color.White)
                    Spacer(modifier = Modifier.height(10.dp))
                        Button(onClick = { navIdea() },
                            shape = RoundedCornerShape(4), colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
                            Text(text = "Ide", color = Color(myColourString.toColorInt()))
                        }
                    }
            }
        )
    }
}
