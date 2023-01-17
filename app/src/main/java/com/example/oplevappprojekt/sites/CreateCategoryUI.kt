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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.ViewModel.IdeasViewModel
import com.example.oplevappprojekt.ViewModel.Journeysviewmodel
import com.example.oplevappprojekt.data.category
import com.example.oplevappprojekt.data.uid


// to create category
@Composable
fun createcat(navDash: () -> Unit, viewModel: Journeysviewmodel, ideasViewModel: IdeasViewModel, navBack: ()->Unit){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .background(Color(myColourString.toColorInt()))
                .height(350.dp)
                .width(350.dp),

            ) {
            Box(modifier = Modifier
                .size(30.dp)
                .absoluteOffset(x = 320.dp, y = 0.dp)){
                Image(painter = painterResource(id = R.drawable.close), contentDescription = "", modifier = Modifier.fillMaxSize().clickable(onClick = {navBack()}))
            }
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
                    var text = ""
                    if(ideasViewModel.uiState.value.isCategorySelected){
                        text = ideasViewModel.uiState.value.categoryName
                    }
                    val name = nameCat(text = text)
                    Text(ideasViewModel.uiState.value.addMessage)
                    var enabled = false
                    if(name.isNotEmpty()){
                        enabled=true
                    }
                    var ID = viewModel.uiState.value.currentJourneyID
                    if(!viewModel.uiState.value.isOwned){
                        ID = viewModel.uiState.value.originalJourneyID
                    }
                    var OnClick = {
                        ideasViewModel.setcategory(title = name, ID = ID.toString())
                        System.out.println("in the wrong place")
                        navDash()
                        // ideasViewModel.deselect()
                    }
                    println("in createcat: " + ideasViewModel.uiState.value.isCategorySelected)
                    println("in createcat: " + ideasViewModel.uiState.value.categoryName)
                    if(ideasViewModel.uiState.value.isCategorySelected){
                      OnClick = {viewModel.editCategory(name = name, ID = ideasViewModel.uiState.value.categoryID )
                      // navDash()
                     // ideasViewModel.deselect()
                      }
                    }
                    Button(onClick = OnClick, enabled = enabled,
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
fun nameCat(text: String) : String{
val thistext =  remember { mutableStateOf(text) }
TextField(
value = thistext.value,
colors = TextFieldDefaults.textFieldColors(
backgroundColor = Color.White,
cursorColor = Color.Black,
focusedIndicatorColor = Color.Black,
unfocusedIndicatorColor = Color.Transparent),
modifier = Modifier
    .height(60.dp)
    .width(250.dp)
    .offset(x = 2.dp),
shape = RoundedCornerShape(8.dp),
onValueChange = {thistext.value = it
},
label ={
    Text(text = "Titel:",
        color = Color.Gray,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold)
},

)
return thistext.value
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun catCardList(navLoad: ()->Unit,
    catList: ArrayList<category>, viewModel: IdeasViewModel, navIdeas: () -> Unit,
navEdit: ()->Unit, journeysviewmodel: Journeysviewmodel) {
    val itemsinColumn = mutableListOf<ComposableFun>()
    for (category in catList) {
        val tempIdea: ComposableFun = {
            catCard(category=catList.get(0), viewModel, navIdeas, navEdit = navEdit,
            navLoad = navLoad, journeysviewmodel = journeysviewmodel)
        }
        itemsinColumn.add(tempIdea)
    }
    Spacer(modifier = Modifier.height(0.dp))
    Box(modifier = Modifier
        .height(140.dp)
        .width(380.dp) ){
    LazyVerticalGrid(cells = GridCells.Fixed(1)) {

        itemsinColumn.forEachIndexed { index, function ->
            item { catCard(category = catList.get(index), viewModel = viewModel, navIdeas,
                navEdit = navEdit, navLoad = navLoad, journeysviewmodel = journeysviewmodel ) }
        } } }}
@Composable
fun catCard(category: category, viewModel: IdeasViewModel,
            navIdeas: ()->Unit, navLoad: () -> Unit, journeysviewmodel: Journeysviewmodel,
navEdit: () -> Unit){
    Card(modifier = Modifier
        .height(52.dp)
        .width(200.dp)
        .padding(vertical = 2.dp)
        .clickable(onClick = {
            viewModel.selectCat(ID = category.categoryID, name = category.name)
            viewModel.deselectIdea()
            println(viewModel.uiState.value.ideatitle)
            navIdeas()
        })
        , elevation = 4.dp) {

        Box(modifier = Modifier.background(color = Color(myColourString.toColorInt()))) {
            Text(
                text = category.name,
                modifier = Modifier.padding(16.dp),
                //      .border(width = 2.dp, shape = , color = Color.Black),
                style = MaterialTheme.typography.h3,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                //fontFamily = FontFamily.Cursive,
                //  fontFamily = FontFamily.Serif
                color = Color.White,
                //  textDecoration = TextDecoration.Underline
            )
            Row() {
                Button(onClick = {
                    viewModel.selectCat(ID = category.categoryID,
                        name = category.name)
                    //navLoad()
                    journeysviewmodel.deleteCategory(category.categoryID) },
                    modifier = Modifier.offset(x=310.dp),
                    colors = ButtonDefaults.buttonColors(Color(colorRed.toColorInt()))) {
                    Text(text="Slet", color = Color.White)
                }
            Button(onClick = { navEdit()
            viewModel.selectCat(category.categoryID, name= category.name )},modifier = Modifier.offset(x=150.dp), colors = ButtonDefaults.buttonColors(Color.Gray)) {
                Text(text="Rediger", color = Color.White)
            }}
       } }
}