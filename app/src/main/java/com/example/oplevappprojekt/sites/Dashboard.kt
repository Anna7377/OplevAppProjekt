package com.example.oplevappprojekt.sites

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.rememberImagePainter
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.ViewModel.CollaboratorViewmodel
import com.example.oplevappprojekt.ViewModel.IdeasViewModel
import com.example.oplevappprojekt.ViewModel.Journeysviewmodel
import com.example.oplevappprojekt.ViewModel.ideas
import com.example.oplevappprojekt.data.IdeaRepository
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
    navProfile: () -> Unit,
    navLoad: () -> Unit,
    navCreateIdea: ()->Unit
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
                viewModel.getCategories()
                TopCard(
                    ImageId =
                    viewModel.uiState.value.currentImg,
                    text = viewModel.uiState.value.currentcountry.toString(),
               navMain = navMain, viewModel = viewModel
                )
                var categories = viewModel.uiState.value.categorylist
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
                    categories = viewModel.uiState.value.categorylist
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
                    navLoad = navLoad,
                    navIdeas = navCatIdeas,
                    navEdit = createCat,
                    journeysviewmodel = viewModel
                )
                IdeaGrid(list = ideas, randomimg = viewModel.randomImg(),
                    viewModelIdea, navLoad = navLoad, navCreateIdea)
            }
        }
    },
        floatingActionButton = {
            Fob(navCreate = navCreate)
        })

}



 @OptIn(ExperimentalFoundationApi::class)
@Composable
fun IdeaGrid(list: ArrayList<ideas>, randomimg: Int, viewModel: IdeasViewModel,
 navLoad: ()->Unit, navCreate: () -> Unit){
    val itemsinColumn = mutableListOf<ComposableFun>()

   for (idea in list){
       val tempIdea: ComposableFun = {
           IdeaBox(idea = idea, randomimg = randomimg, viewModel,
               navLoad = navLoad, navCreate = navCreate)
       }
       itemsinColumn.add(tempIdea)
   }
    LazyVerticalGrid(cells = GridCells.Fixed(2)){

        itemsinColumn.forEachIndexed{
                index, function ->  item { IdeaBox(list.get(index),
            randomimg, viewModel, navLoad = navLoad, navCreate = navCreate) }
        }
    }
}




@Composable
fun IdeaBox(idea: ideas, randomimg: Int, viewModel: IdeasViewModel,
            navLoad:()->Unit, navCreate: () -> Unit) {
    val dialog = remember{ mutableStateOf(false) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .clickable(onClick = { dialog.value = true
                viewModel.selectIdea(idea.ID, desc = idea.desc, title = idea.title,
                    img = idea.img.toString(), link = idea.link)})
            .width(190.dp)
            .height(190.dp)
            .padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
            .clip(RoundedCornerShape(15))
            .background(color = Color(myColourString.toColorInt())))

        {
            val imgpainter = rememberImagePainter(data = idea.img)
            if(idea.img?.isNotEmpty()==true){
                Image(painter = imgpainter, contentDescription = null, modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds)
            }
            else{
            Image(
                painter = painterResource(id =R.drawable.logo_photo ),
                contentDescription = " ", contentScale = ContentScale.FillHeight
            ) }

        }
        Text(text=idea.title, textAlign = TextAlign.Center)


        }
    if(dialog.value){
        AlertDialog(onDismissRequest = {dialog.value=false},
            title = {
                var text = "Unavngivet"
                println(idea.title + idea.desc)
                if(idea.title.isNotEmpty()){
                    text = idea.title
                }
                Text(text=text, color = Color.White)
            },
            text={
                Column() {
                    Button(
                        onClick = {navCreate()},
                        colors = ButtonDefaults.buttonColors(Color.Gray),
                        modifier = Modifier
                            .absoluteOffset(x = 0.dp, y = 115.dp)
                            .height(35.dp)
                            .width(85.dp)
                            .padding(bottom = 5.dp)
                    ) {
                        Text(
                            text = "Rediger",
                            color = Color.White,
                            fontSize = 12.sp,
                            // fontWeight = FontWeight.Bold
                        )
                    }
                    Button(
                        onClick = {
                            viewModel.deleteIdea(idea.ID)
                         //   navLoad()
                            dialog.value=false
                        },
                        colors = ButtonDefaults.buttonColors(Color(colorRed.toColorInt())),
                        modifier = Modifier
                            .absoluteOffset(x = 0.dp, y = 115.dp)
                            .height(35.dp)
                            .width(85.dp)
                            .padding(top=5.dp)
                    ) {
                        Text(
                            text = "Slet",
                            color = Color.White,
                            fontSize = 12.sp,
                            // fontWeight = FontWeight.Bold
                        )
                    }
                    SelectionContainer() {
                        Text(text = idea.desc + "" +  idea.link
                            , color = Color.White)
                    }


                    if (idea.img?.isNotEmpty() == true) {
                        Image(
                            painter = rememberImagePainter(data = idea.img),
                            contentDescription = null
                        )
                    }
                    else{
                        Text(text="Intet Billede")
                    }
                    }
                 },


            confirmButton = { TextButton(onClick = {dialog.value=false}, modifier = Modifier.padding(bottom = 10.dp, start = 10.dp)) { Text(text="Luk", color = Color.White) } },
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
        viewModel.deleteJourney()
        navMain()
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
                        Button(onClick = { navIdea()
                                         ideasViewModel.deselect()
                                         ideasViewModel.deselectIdea()},
                            shape = RoundedCornerShape(4), colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
                            Text(text = "Ide", color = Color(myColourString.toColorInt()))
                        }
                }
            }
        )
    }
}
