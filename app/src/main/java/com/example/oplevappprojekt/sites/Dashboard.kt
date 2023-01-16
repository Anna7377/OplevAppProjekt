package com.example.oplevappprojekt.sites

import android.widget.Space
import androidx.appcompat.app.AlertDialog
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.ViewModel.CollaboratorViewmodel
import com.example.oplevappprojekt.ViewModel.IdeasViewModel
import com.example.oplevappprojekt.ViewModel.Journeysviewmodel
import com.example.oplevappprojekt.ViewModel.ideas
import java.util.*


typealias ComposableFun = @Composable () -> Unit
var countryname = ""
var journeyID = " "
val colorRed = "#C40007"

//s215722
@Composable
fun MyJourneyPage(
    navCreate: () -> Unit,
    viewModel: Journeysviewmodel,
    viewModelIdea: IdeasViewModel,
    viewModelcol: CollaboratorViewmodel,
    navEdit: () -> Unit,
    navMain: () -> Unit,
    navCatIdeas: () -> Unit,
    createCat: () -> Unit,
    navProfile: () -> Unit
){
    Scaffold(bottomBar = {BottomBar(onClick1 = {}, onClick2 = {navMain()}, onClick3 = {navProfile()})},
        content =
        {
    //Scaffold(content = {
        Surface {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                countryname = viewModel.uiState.value.currentcountry.toString()
                if(viewModel.uiState.value.isOwned){
                journeyID = viewModel.uiState.value.currentJourneyID.toString()}
                else{
                    journeyID = viewModel.uiState.value.originalJourneyID
                }
                TopCard(
                    ImageId =
                    viewModel.uiState.value.currentImg,
                    text = viewModel.uiState.value.currentcountry.toString(),
                viewModel = viewModel,
                    navMain

                )
                var categories = viewModel.getCategories()
                var ideas = viewModel.getOtherIdeas()
                if (viewModel.uiState.value.isOwned) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        showCol(viewModel = viewModelcol, ID = viewModel.uiState.value.currentJourneyID.toString())
                        Spacer(modifier = Modifier.width(20.dp))
                        genLink(viewModel = viewModel)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        //Spacer(modifier = Modifier.width(30.dp))
                        editJourney(navEdit = { navEdit() })
                        Spacer(modifier = Modifier.width(20.dp))
                        deleteJourney(navMain = { navMain() }, viewModel = viewModel)
                    }
                    Row (verticalAlignment = Alignment.CenterVertically){
                        Text(
                            text = viewModel.uiState.value.currentdate.toString(),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(30.dp, 10.dp)
                        )
                    }
                } else {
                    ideas = viewModel.getColIdeas()
                    categories = viewModel.getColCategories()
                    Row{
                    uncollab(
                        viewModel = CollaboratorViewmodel(),
                        orig = viewModel.uiState.value.currentJourneyID.toString()
                    ) {
                    }
                        Text(
                            text = viewModel.uiState.value.currentdate.toString(),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(30.dp, 10.dp)
                        )
                    }
                }
                catCardList(
                    catList = categories,
                    viewModel = viewModelIdea,
                    navCatIdeas,
                    navEdit = createCat
                )
                IdeaGrid(list = ideas)
            }
        }
    },
        floatingActionButton = {
            Fob(navCreate = navCreate)
            //viewModelIdea.deselect()
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
            .width(190.dp)
            .height(190.dp)
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
    Button(onClick = {navEdit()}, colors = ButtonDefaults.buttonColors(Color(myColourString.toColorInt())),
        modifier = Modifier
            .height(35.dp)
            .width(171.dp)) {
        Text(text="Rediger Rejse", color = Color.White)
    }
}
@Composable
fun deleteJourney(navMain: ()-> Unit, viewModel: Journeysviewmodel) {
    Button(onClick = {
        navMain()
        viewModel.deleteJourney()
    }, colors = ButtonDefaults.buttonColors(Color(colorRed.toColorInt())),modifier = Modifier
        .height(35.dp)
        .width(179.dp)) {
        Text(text="Slet Rejse", color = Color.White)
    } }

@Composable
fun genLink(viewModel: Journeysviewmodel){
    val dialog = remember{mutableStateOf(false)}

    if(dialog.value){
        AlertDialog(onDismissRequest = {dialog.value=false},
            title = { Text(text="Inviter medarrangør via linket:", color = Color.White) },
            text={ SelectionContainer() {
                Text(text= viewModel.uiState.value.currentJourneyID.toString(),
                color = Color.White, ) }},
            confirmButton = { TextButton(onClick = {dialog.value=false}) { Text(text="luk", color = Color.White) } },
            backgroundColor = Color(myColourString.toColorInt()))
    }
    Button(onClick = {dialog.value=true}, colors = ButtonDefaults.buttonColors(Color(myColourString.toColorInt()))) {
Text("Inviter Medarrangør", color = Color.White)
    }
}

@Composable
fun showCol(viewModel: CollaboratorViewmodel, ID: String){
    val dialog = remember{mutableStateOf(false)}

    if(dialog.value){
        AlertDialog(onDismissRequest = {dialog.value=false},
            title = { Text(text="Medarrangørere", color = Color.White) },
            text={ SelectionContainer() {
               Text(text= viewModel.showCol(ID).toString(),
                    color = Color.White, ) }},
            confirmButton = { TextButton(onClick = {dialog.value=false}) { Text(text="luk", color = Color.White) } },
            backgroundColor = Color(myColourString.toColorInt()))
    }
    Button(onClick = {dialog.value=true},
        colors = ButtonDefaults.buttonColors(Color(myColourString.toColorInt()))) {
        Text("Se Medarrangørere", color = Color.White)
    }
}



@Composable
fun uncollab(viewModel: CollaboratorViewmodel, orig: String, navMain: () -> Unit){
    Button(onClick = {  viewModel.uncollab(orig)
   navMain() }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(myColourString.toColorInt()))){
        Text("fjern rejse", color = Color.White)
    }

}

@Composable
fun createOpt(navCat: ()->Unit, navIdea: ()->Unit, ideasViewModel: IdeasViewModel, navBack: ()->Unit){
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
            content =
            {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(onClick = { navCat()
                                         ideasViewModel.deselect()},
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
